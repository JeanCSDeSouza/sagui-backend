package br.com.unirio.sagui.pdfIo;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;
@ToString
public class StudentMap {
	@Getter private Map<String, StudentFromPdf> students;
	public StudentMap() {
		students = new HashMap<String,StudentFromPdf>();
	}
	public boolean addAluno(StudentFromPdf student) {
		if(!students.containsKey(student.getMatricula())) {
			students.put(student.getMatricula(), student);
			return true;
		}
		return false;
	}
	public DisciplineMap getDisciplines(String matricula) {
		return students.get(matricula).getDisciplines();
	}
	public StudentFromPdf getStudent(String matricula) {
		return students.get(matricula);
	}
}
