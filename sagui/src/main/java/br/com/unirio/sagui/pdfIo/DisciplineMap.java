package br.com.unirio.sagui.pdfIo;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;
@ToString
public class DisciplineMap {
	@Getter private Map<String, DisciplineFromPdf> disciplines;
	public DisciplineMap() {
		disciplines = new HashMap<String,DisciplineFromPdf>();
	}
	
	public void addDiscipline(DisciplineFromPdf discipline) {
		if( !disciplines.containsKey(discipline.getCode()))
			disciplines.put(discipline.getCode(), discipline);
		else {
			disciplines.get(discipline.getCode()).addAttendance( discipline.getGrade() , discipline.getStatusCode(), discipline.getStatusDescription() );
		}
	}

	public DisciplineFromPdf getDiscipline(String code) {
		return disciplines.get(code);
	}
}
