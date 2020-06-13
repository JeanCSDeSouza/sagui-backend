package br.com.unirio.sagui.pdfjobs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.unirio.sagui.model.Aluno;
import br.com.unirio.sagui.model.CrPeriodizado;
import br.com.unirio.sagui.model.DisciplinaCursada;
import br.com.unirio.sagui.model.Grade;
import br.com.unirio.sagui.pdfIo.StudentFromPdf;
import br.com.unirio.sagui.pdfupload.FileStorageProperties;
import br.com.unirio.sagui.repository.AlunoRepository;
import br.com.unirio.sagui.repository.CrPeriodizadoRepository;
import br.com.unirio.sagui.repository.DisciplinaCursadaRepository;
import br.com.unirio.sagui.repository.GradeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DisallowConcurrentExecution
public class DataMigration extends QuartzJobBean{
	
	@Autowired
	private FileStorageProperties fileStorageProperties;
	
	@Autowired
	private AlunoRepository alunoDao;
	
	@Autowired
	private DisciplinaCursadaRepository disciplinaCursadaDao;
	
	@Autowired
	private GradeRepository gradeDao;
	
	@Autowired
	private CrPeriodizadoRepository crPeriodizadoDao;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("DataMigrationCron Started................");
		//Read
		try {
			if( Files.list(Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize()).count() == 0 ) {
				
				Files.newDirectoryStream(Paths.get(fileStorageProperties.getProcessedDir()).toAbsolutePath().normalize(),
						path -> path.toString().endsWith(".txt")).forEach( file ->{
							
							try {
								FileInputStream fileInputStream = new FileInputStream(file.toString());
							    ObjectInputStream studentInputStream = new ObjectInputStream(fileInputStream);
							    StudentFromPdf student = (StudentFromPdf) studentInputStream.readObject();
							    studentInputStream.close();
							    log.info(student.toString());
							    studentFromPdfToAluno(student);
							    Files.deleteIfExists(file);
							}catch(IOException e1) {
								log.error(e1.getMessage());
								log.error(e1.getCause().toString());
							} catch (ClassNotFoundException e) {
								log.error("O arquivo" + file.toString() + " não pôde ser desserializado em um Aluno devido à: " + e.getCause().toString());
							}
							
						});
			}
		} catch (IOException e) {
			log.error( e.getMessage() );
		}
		log.info("DataMigrationCron Finished................");
	}
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = {ConstraintViolationException.class, Exception.class}, readOnly = false)
	private void studentFromPdfToAluno(StudentFromPdf student) {
		try {
			Aluno aluno = new Aluno();
			Long doesExists =  alunoDao.countByMatricula(student.getMatricula());
			String currentAlunoId = "create";
			if( doesExists > 0 ) {
				aluno = alunoDao.findByMatricula( student.getMatricula() );
				currentAlunoId = aluno.getAluno_Id();
			}
			final String alunoIdFlag = currentAlunoId;
			//If a student wasn't retrived from database then gets the data from the serialized txt
			if( alunoIdFlag.equals("create") ) {
				//Translate a sudent from the serialized txt to br.com.unirio.sagui.model.Aluno
				aluno.setNome(student.getNome());
				aluno.setMatricula(student.getMatricula());
				aluno.setEmail(student.getNome()+"@UNIRIOTEC.BR");
				aluno.setEntrada_Ano_Periodo( student.getMatricula().substring( 0, 5 ) );
				aluno.setCurso( student.getMatricula().substring( 5, 8 ) );
				aluno.setCra(0);
				aluno = alunoDao.save(aluno);
				alunoDao.flush();
			}else {
				aluno = alunoDao.findByMatricula( student.getMatricula());
			}
			final Aluno persistedAluno = aluno;
			Set<CrPeriodizado> crsPeriodizado = new HashSet<CrPeriodizado>();
			student.getNotas().forEach( v -> {
				CrPeriodizado crPeriodizado = new CrPeriodizado();
				if( crPeriodizadoDao.countByAlunoIdAndPeriodo( persistedAluno.getAluno_Id(), v.getDescricao() ) > 0 ) {
					crPeriodizado = crPeriodizadoDao.findByPeriodoAndAluno( v.getDescricao(), persistedAluno);
				}else {
					crPeriodizado.setPeriodo( v.getDescricao() );
				}
				crPeriodizado.setCargaCreditos( v.getCargaDeCreditos().toString() );
				crPeriodizado.setCargaHoraria( v.getCargaDeCreditos().toString() );
				crPeriodizado.setCr( v.getNota() );
				crPeriodizado.setAluno(persistedAluno);
				log.info("CRPERIODIZADO AQUI: ===================" + crPeriodizado.toString());
				crsPeriodizado.add(crPeriodizado);
			});
			log.info("NOTAAAAAAAAAAAAAAAA: +++++++++++++++++++++++++++++++++++++++++         "+aluno.getAluno_Id() );
			//This set will be used to cache all the student's disciplines 
			Set<DisciplinaCursada> disciplinas = new HashSet<DisciplinaCursada>();
			//Runs through all the disciplines to translate them to br.com.unirio.sagui.model.DisciplinaCursada 
	 		student.getDisciplines().getDisciplines().forEach( (k,v) -> {
				DisciplinaCursada disciplina = new DisciplinaCursada();
				disciplina.setCodigo(v.getCode());
				disciplina.setAluno(persistedAluno);
				Long gradeExists = gradeDao.countByCodigo(v.getCode());
				Long alunoDisciplinaExists = disciplinaCursadaDao.countByCodigoAndAlunoId(v.getCode(), alunoIdFlag);
				if (alunoDisciplinaExists > 0) {
					disciplina = disciplinaCursadaDao.findByCodigoAndAlunoId(v.getCode(),  persistedAluno.getAluno_Id() );
				}
				if( gradeExists > 0 ) {
					Grade grade = gradeDao.findByCodigo( v.getCode() );
					log.info(v.getCode() + "===============" + v.getStatusCode());
					disciplina.setCurso( grade.getCurso() );
					disciplina.setVersaoGrade( grade.getVersaoGrade() );
					disciplina.setTipo(grade.getTipo());
					disciplina.setTitulo(grade.getNome());
					disciplina.setPeriodo(grade.getPeriodo());
					disciplina.setCreditos(grade.getCreditos());
				} 
				//Disciplines always change then, always refresh with the serialized txt
				disciplina.setNota( ( v.getGrade() == null )? 0 : v.getGrade() );//Grade != br.com.unirio.sagui.model.Grade  (a > 0) ? 1 : 2;
				log.info("NOTAAAAAAAAAAAAAAAA: +++++++++++++++++++++++++++++++++++++++++         "+v.getGrade() );
				disciplina.setQtdCursada(v.getTimesAttended().shortValue());
				disciplina.setQtdReprovacao(v.getTimesFailure().shortValue());
				
				//TODO Here some translation would come in hand - Ex.: APV(what comes from de pdf reader) to Aprovado(What shoudl be persisted in the database
				//For a hint why this wasn't done look in the br.com.unirio.sagui.pdfIo.DisciplineStatus. It is ambiguous
				//disciplina.setSituacao(v.getStatusCode());
				for( DisciplineTranslator dt : DisciplineTranslator.values() )
					if(dt.name().equals(v.getStatusCode()))
						disciplina.setSituacao( dt.getStatusName() );
				disciplinas.add(disciplina);
			});
	 		aluno.setCrPeriodizados(crsPeriodizado);
	 		aluno.setDisciplinaCursadas(disciplinas);
			alunoDao.save(aluno);
		}catch(NullPointerException npe) {
			log.error(npe.getMessage());
			log.error(npe.getCause().toString());
		}
	}
	
}
