package br.com.unirio.sagui.pdfIo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdfMineLines {    
	/**
     * Groups:
     * #1 - Code
     * #2 - Name
     * #3 - CR
     * #4 - CH
     * #5 - Grade
     * #6 - Attendance
     * #7 - Status
     * #8 - Status description
     */
	private static Pattern disciplineRegex = Pattern.compile("([A-Z]{3}[0-9]{4})\\s(.*?)\\s([0-9])\\s([0-9]{2})\\s([0-9]{1,2}\\,[0-9]{2})?\\s?-?([0-9]{1,3}\\,[0-9]{2})\\s([A-Z]{3})\\s?-\\s?(.*)");
	 /**
     * Groups:
     * #1 - Code
     * #2 - Name
     * #3 - CR
     * #4 - CH
     * #5 - Status 
     */
	private static Pattern trtGeral = Pattern.compile("(TRT[0-9]{4})\\s(.*?)\\s([0-9])\\s([0-9])\\s\\s\\s([A-Z]{3})");
	private static Pattern studentCodeRegex = Pattern.compile("Matrícula:\\s([0-9]{11})");
    private static Pattern studentNameRegex = Pattern.compile("Nome Aluno:\\s([a-zA-Z\\s]*)");
    //Curso: 210 - Sistemas de Informação - Bacharelado - Turno Integral (V/N) - código e-MEC 20065 Versão: 2008/1
    private static Pattern cabecalhoCurso = Pattern.compile("Curso:\\s([0-9]{1,3})\\s-\\s(.*)\\s-\\s(.*)\\s-\\s(.*)\\s-\\s(.*)");
    //Créditos Carga Horária
    private static Pattern endOfPdf = Pattern.compile("Créditos Carga Horária");
	
    private StudentMap students;
    private String matricula;
    private String name;
    
    public PdfMineLines() {
		students = new StudentMap();
	}
    public StudentMap mineLines(List<String> lines) {
    	//TODO
    	for( int i = 0; i < lines.size(); i++){
    		if(lines.size() > i+1)//prevents arrayoutofbounds when in the last
    			ProcessStudentCodeRegex(lines.get(i), lines.get(i+1));//Code and name are subsequent, then findin code, nest is name
    		if( matricula != null && name != null && !students.addAluno( StudentFromPdf.builder().matricula(matricula).nome(name).build() ) ) {
    			processDisciplineRegex(lines.get(i));
    			processTrtRegex(lines.get(i));
    		}

    		
    	}
    	return students;
    }
    
	private void processDisciplineRegex(String line) {
		// TODO Auto-generated method stub
		
		Matcher matcher = disciplineRegex.matcher(line);
		if(matcher.find()) {
		students.getDisciplines( matricula )
			.addDiscipline( DisciplineFromPdf.builder()
			.code(  matcher.group(1) )
			.cr( Double.parseDouble( matcher.group(3) ) )
			.grade(  (matcher.group(5) == null)? 0 : Double.parseDouble( matcher.group(5).replace(",", ".") ) )
			.statusCode( matcher.group(7) )
			.statusDescription( matcher.group(8) )
			.build() );
		}
	}
	private void processTrtRegex(String line) {
		Matcher matcher = trtGeral.matcher(line);
		if(matcher.find()) {	
		students.getDisciplines( matricula )
			.addDiscipline( DisciplineFromPdf.builder()
			.code(  matcher.group(1) )
			.cr( Double.parseDouble( matcher.group(3).replace(",", ".") ) )
			.statusCode( matcher.group(5) )
			.build() );
		}
	}
	private void ProcessStudentCodeRegex(String line ,String nextLine) {
		// TODO Auto-generated method stub
		Matcher matcher = studentCodeRegex.matcher(line);
		if(matcher.find()) {
			this.matricula = matcher.group(1);
			ProcessStudentNameRegex(nextLine);
		}
	}	
	private void ProcessStudentNameRegex(String nextLine) {
		// TODO Auto-generated method stub
		Matcher matcher = studentNameRegex.matcher(nextLine);
		if( matcher.find() ) {
			this.name = matcher.group(1);
		}
	}
	private void ProcessInstitutionHeaderRegex(String line) {
		// TODO Auto-generated method stub
	}
    
}
