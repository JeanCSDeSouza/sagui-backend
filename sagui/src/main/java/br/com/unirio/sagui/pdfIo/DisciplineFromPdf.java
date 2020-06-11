package br.com.unirio.sagui.pdfIo;

import java.io.Serializable;

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
public class DisciplineFromPdf implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6613256938308752222L;
	@Getter private String code;
    //@Getter private String name;
    @Getter@EqualsAndHashCode.Exclude private Double cr;
    //@Getter private String ch;
    @Getter@EqualsAndHashCode.Exclude private Double grade;
    @Getter@EqualsAndHashCode.Exclude private String statusCode;
    @Getter@EqualsAndHashCode.Exclude private String statusDescription;
    @Getter@EqualsAndHashCode.Exclude private Integer timesAttended;
    @Getter@EqualsAndHashCode.Exclude private Integer timesFailure;
    
    @Builder
    public DisciplineFromPdf(String code, double cr, double grade, String statusCode, String statusDescription) {
    	this.timesAttended = 0;
    	this.timesFailure = 0;
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
    	this.grade = grade;this.statusCode = statusCode; this.statusDescription = statusDescription;
    	if( statusCode.equals(DisciplineStatus.APROVADO.getStatusName()) || statusCode.equals(DisciplineStatus.DISPENSA_SEM_NOTA.getStatusName()) || statusCode.equals(DisciplineStatus.TRANCADO.getStatusName()) ) {
    		oneMoreAttendence(); 
    		if( statusDescription != null && statusDescription.contains(DisciplineStatus.STATUS_DESCRIPTION_DIFF_TRA_TR.getStatusName())) {
    			this.statusCode = DisciplineStatus.STATUS_DESCRIPTION_DIFF_TRA_TR.getStatus();
    			this.timesAttended =  new Integer(this.timesAttended.intValue() - 1);
    		}
    	}else if( statusCode.equals(DisciplineStatus.REPROVADO.getStatusName() ) || statusCode.equals(DisciplineStatus.REPROVADO_POR_FALTA.getStatusName() ) || statusCode.equals(DisciplineStatus.REPROVADO_POR_NOTA.getStatusName() )) {
    		oneMoreAttendence(); oneMoreFailure();
    	}else if( statusCode.equals( DisciplineStatus.REPROVADO_SEM_NOTA.getStatusName() ) && statusDescription.equals(DisciplineStatus.STATUS_DESCRIPTION_DIFF_ASC_PRV.getStatusName()) ) {
    		this.statusCode = DisciplineStatus.STATUS_DESCRIPTION_DIFF_ASC_PRV.getStatus();
    		oneMoreAttendence(); oneMoreFailure();
    	}
    }
    private void oneMoreAttendence() {
    	this.timesAttended =  new Integer(this.timesAttended.intValue() + 1);
    }
    private void oneMoreFailure() {
    	this.timesFailure =  new Integer(this.timesFailure.intValue() + 1);
    }
}
