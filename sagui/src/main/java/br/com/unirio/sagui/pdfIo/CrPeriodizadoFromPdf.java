package br.com.unirio.sagui.pdfIo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class CrPeriodizadoFromPdf implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6992393577615676875L;
	@Getter private String descricao;
	@Getter @EqualsAndHashCode.Exclude private Integer cargaDeCreditos;
	@Getter @EqualsAndHashCode.Exclude private Integer cargaHoraria;
	@Getter @EqualsAndHashCode.Exclude private Double nota;

}
