package br.com.unirio.sagui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.unirio.sagui.model.DisciplinaCursada;

@Repository
public interface DisciplinaCursadaRepository extends JpaRepository<DisciplinaCursada, Integer>{
	/**
	 * Return a student by his code number
	 * @Code - Discipline code ex.: TIN1155
	 */
	DisciplinaCursada findByCodigo(String matricula);
	/**
	 * Returns the registry of a discipline coursed by the student 
	 * @param codigo Discipline unique code
	 * @param AlunoId Student identifier code
	 * @return
	 */
	@Query("SELECT d FROM DisciplinaCursada d WHERE d.codigo = :codigo AND d.aluno.aluno_Id = :alunoId")
	DisciplinaCursada findByCodigoAndAlunoId(
			@Param("codigo") String codigo, 
			@Param("alunoId") String aluno_aluno_Id);
	/**
	 * Counts the number of a determined disciplines appears to a determined student
	 * @see used to validade the existence of a discipline for an studet, thus should always be 0 or 1
	 * @param codigo Discipline unique code
	 * @param AlunoId Student identifier code
	 * @return Long number of occurrence of a discipline in the database
	 */
	@Query("SELECT COUNT(d) FROM DisciplinaCursada d WHERE d.codigo = :codigo AND d.aluno.aluno_Id = :alunoId")
	Long countByCodigoAndAlunoId(@Param("codigo") String codigo, @Param("alunoId") String aluno_Aluno_Id);
}
