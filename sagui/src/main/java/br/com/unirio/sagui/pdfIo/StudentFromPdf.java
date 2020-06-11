package br.com.unirio.sagui.pdfIo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
public class StudentFromPdf implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1345541711085208640L;
	@Getter @EqualsAndHashCode.Exclude private String nome;
	@Getter private String matricula;
	@Getter @EqualsAndHashCode.Exclude private Set<CrPeriodizadoFromPdf> notas;
	@Getter @EqualsAndHashCode.Exclude private DisciplineMap disciplines;
	
	@Builder
	public StudentFromPdf (String matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
		this.disciplines = new DisciplineMap();
		this.notas = new HashSet<CrPeriodizadoFromPdf>();
	}
	
	public void addDisclipline(DisciplineFromPdf discipline) {
		this.disciplines.addDiscipline(discipline);
	}
	public boolean addCrPeriodizado(CrPeriodizadoFromPdf nota) {
		if(!notas.contains(nota)) {
			notas.add(nota);
			return true;
		}else
			return false;
			
		
	}
}
