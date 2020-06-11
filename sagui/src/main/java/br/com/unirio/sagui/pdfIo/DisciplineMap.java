package br.com.unirio.sagui.pdfIo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;
@ToString
public class DisciplineMap implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6043365422525888469L;
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
