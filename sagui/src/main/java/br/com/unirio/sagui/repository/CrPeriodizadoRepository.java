package br.com.unirio.sagui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.unirio.sagui.model.Aluno;
import br.com.unirio.sagui.model.CrPeriodizado;

public interface CrPeriodizadoRepository extends JpaRepository<CrPeriodizado, Integer>{
	CrPeriodizado findByAluno(String Matricula);
	/**
	 * Counts CrPeriodizados. 
	 * @see used to validade the existence of a determined Grade on CrPeriodizado table, thus should always be 0 or 1
	 * @param codigo Discipline unique code
	 * @return Long number of occurrence of grades in the database
	 */
	@Query("SELECT COUNT(c) FROM CrPeriodizado c WHERE c.aluno.aluno_Id = :alunoId AND c.periodo = :periodo")
	Long countByAlunoIdAndPeriodo(@Param("alunoId") String aluno_Aluno_Id, @Param("periodo") String periodo);
	
	CrPeriodizado findByPeriodoAndAluno(String Periodo, Aluno AlunoId);
}
