package br.com.unirio.sagui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.unirio.sagui.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer>{
	Grade findByCodigo(String codigo);
	/**
	 * Counts disciplines in the Grade Table. 
	 * @see used to validade the existence of a discipline on Grade table, thus should always be 0 or 1
	 * @param codigo Discipline unique code
	 * @return Long number of occurrence of a discipline in the database
	 */
	@Query("SELECT COUNT(g) FROM Grade g WHERE g.codigo = :codigo")
	Long countByCodigo(@Param("codigo") String codigo);
}
