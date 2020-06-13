package br.com.unirio.sagui.pdfjobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import br.com.unirio.sagui.pdfIo.PDFIO;
import br.com.unirio.sagui.pdfIo.PdfMineLines;
import br.com.unirio.sagui.pdfIo.StudentMap;
import br.com.unirio.sagui.pdfupload.FileStorageProperties;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Slf4j
@DisallowConcurrentExecution
public class PdfProcessorJob extends QuartzJobBean {
	@Autowired
	private FileStorageProperties fileStorageProperties;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("PdfProcessor Started................");
		try {
			Files.newDirectoryStream(Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize(),
					path -> path.toString().endsWith(".pdf")).forEach( file -> {
						PDFIO pdf = new PDFIO(file.toString());
						
						PdfMineLines pdfMineLines = new PdfMineLines();
						StudentMap studentMap =  pdfMineLines.mineLines(pdf.getLinesList());
						studentMap.getStudents().forEach( (k,v) ->  {
							FileWriter createTxt;
							try {
								createTxt = new FileWriter(Paths.get(fileStorageProperties.getProcessedDir()).toAbsolutePath().toString() + "\\" + k +"_" + v.getNome() +".txt");
								createTxt.close();
								FileOutputStream prepareForStudentSerialization = 
										new FileOutputStream(Paths.get(fileStorageProperties.getProcessedDir()).toAbsolutePath().toString() + "\\" + k +"_" + v.getNome() +".txt");
								ObjectOutputStream studentSerialization = new ObjectOutputStream(prepareForStudentSerialization);
								studentSerialization.writeObject(v);
								studentSerialization.close();
							} catch (IOException e1) {
								log.error(e1.getMessage());
								log.error(e1.getCause().toString());
							}
						});
						String uploadedFile = file.toFile().toString();
						String newFile = uploadedFile.replace("upload", "processed");
						newFile = newFile.replace(".pdf", "_processed.pdf");
						
						try {
							//After closing the pdf moves to the processed dir
							pdf.closeDocument();
							Files.move(Paths.get(uploadedFile), Paths.get(newFile), StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							log.error(e2.getMessage());;
							log.error(e2.getCause().toString());
						}
					});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		log.info("PdfProcessor Ended................");
	}
}
