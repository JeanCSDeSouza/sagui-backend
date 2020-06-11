package br.com.unirio.sagui.pdfjobs;

import lombok.Getter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DisciplineTranslator {
	DIS("Aprovado"),
	ADI("Aprovado"),
	APV("Aprovado"),
	REF("Reprovado"),
	RPV("Reprovado"),
	REP("Reprovado"),
	TRA("Trancamento Geral"),
	TR("Trancamento de disciplina"),
	ASC("Matriculado");
	
	@Getter String statusName;
}
