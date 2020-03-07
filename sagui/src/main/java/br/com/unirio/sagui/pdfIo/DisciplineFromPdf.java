package br.com.unirio.sagui.pdfIo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
/**
 * Class responsible for wrapping a discipline from the pdf
 * @author Jean carlos
 *
 */
@EqualsAndHashCode
@ToString
public class DisciplineFromPdf {
	
	@Getter private String code;
    //@Getter private String name;
    @Getter@EqualsAndHashCode.Exclude private double cr;
    //@Getter private String ch;
    @Getter@EqualsAndHashCode.Exclude private double grade;
    @Getter@EqualsAndHashCode.Exclude private String statusCode;
    @Getter@EqualsAndHashCode.Exclude private String statusDescription;
    @Getter@EqualsAndHashCode.Exclude private int timesAttended;
    @Getter@EqualsAndHashCode.Exclude private int timesFailure;
    
    @Builder
    public DisciplineFromPdf(String code, double cr, double grade, String statusCode, String statusDescription) {
    	this.code = code; this.cr = cr; this.statusDescription = statusDescription; 
    	addAttendance(grade, statusCode, statusDescription);
    }
    /**
     * When a discipline is already read from the pdf, this method will increase the number of times a student have attended that
     * discipline. It works like an update of a discipline in the moment the pdf are being processed.
     * @param grade
     * @param statusCode
     */ 
    public void addAttendance(Double grade, String statusCode, String statusDescription){
    	// TODO metodo ainda precisa tratar casos dos status da disciplina
    	this.grade = grade;this.statusCode = statusCode; this.statusDescription = statusDescription;
    	if( statusCode.equals(DisciplineStatus.APROVADO.getStatusName()) || statusCode.equals(DisciplineStatus.DISPENSA_SEM_NOTA.getStatusName()) || statusCode.equals(DisciplineStatus.TRANCADO.getStatusName()) ) {//subistituir pelo valor do enum
    		oneMoreAttendence(); 
    	}else if(statusCode.equals(DisciplineStatus.REPROVADO.getStatusName())) {//substituir pelo valor do enum
    		oneMoreAttendence(); oneMoreFailure();
    	}else if( statusCode.equals( DisciplineStatus.REPROVADO_SEM_NOTA.getStatusName() ) && statusDescription.equals(DisciplineStatus.STATUS_DESCRIPTION_DIFF_ASC_PRV.getStatusName()) ) {
    		this.statusCode = DisciplineStatus.STATUS_DESCRIPTION_DIFF_ASC_PRV.getStatus();
    		oneMoreAttendence(); oneMoreFailure();
    	}
    }
    private void oneMoreAttendence() {
    	this.timesAttended++;
    }
    private void oneMoreFailure() {
    	this.timesFailure++;
    }
}
