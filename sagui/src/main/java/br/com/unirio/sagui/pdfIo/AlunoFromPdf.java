package br.com.unirio.sagui.pdfIo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
/**
 * This class represents a student processed from the pdfclob loaded in the application
 * @author Jean carlos
 *
 */
@EqualsAndHashCode
@ToString
public class AlunoFromPdf {
	@Getter @EqualsAndHashCode.Exclude private String nome;
	@Getter private String matricula;
	@Getter @EqualsAndHashCode.Exclude private DisciplineMap disciplines;
	
	@Builder
	public AlunoFromPdf (String matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
		this.disciplines = new DisciplineMap();
	}
	
	public void addDisclipline(DisciplineFromPdf discipline) {
		this.disciplines.addDiscipline(discipline);
	}
}
