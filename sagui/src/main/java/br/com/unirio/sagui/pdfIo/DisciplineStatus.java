package br.com.unirio.sagui.pdfIo;

import lombok.Getter;

/** 
 * This class represents the status of a disciplines with 
 * Approved(1), Reproved(0), Registered(-1), locked(-2). 
 * Dispensation also counts as approved.
 * <p>
 */
public enum DisciplineStatus {
	DISPENSA_SEM_NOTA("DIS", "Aprovado"),
	DISPENSA_COM_NOTA("DIS", "Aprovado"),
	APROVADO("APV", "Aprovado"),
	MATRICULA("ASC", "Matriculado"),
	REPROVADO("RPV", "Reprovado"),
	REPROVADO_POR_FALTA("REF", "Reprovado"),
	REPROVADO_SEM_NOTA("ASC", "Reprovado"),
	TRANCADO("TRA", "Trancamento Geral"),
	STATUS_DESCRIPTION_DIFF_ASC_PRV("Reprovado sem nota", "RPV");
	
	@Getter private String statusName;
	@Getter private String status;
	
	DisciplineStatus( String statusName, String status ) {
		this.statusName = statusName;
		this.status = status;
	}
}
