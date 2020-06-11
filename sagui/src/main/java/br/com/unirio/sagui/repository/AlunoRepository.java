package br.com.unirio.sagui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unirio.sagui.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
	/**
	 * Return a student by his code number
	 * @matricula - student code with 10 characters
	 */
	Aluno findByMatricula(String matricula);
	/**
	 * Counts the number of a determined (by code) student in the database
	 * @see used to validade the existence of a student, thus should always be 0 or 1
	 * @param matricula Student unique code
	 * @return Long number of occurrence of a Student in the database
	 */
	Long countByMatricula(String matricula);
}
