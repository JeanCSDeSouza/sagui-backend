package br.com.unirio.sagui.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the grade database table.
 * 
 */
@Entity
@Table(name="grade")
@NamedQuery(name="Grade.findAll", query="SELECT g FROM Grade g")
public class Grade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GRADE_ID", unique=true, nullable=false)
	private String gradeId;

	@Column(nullable=false, length=45)
	private String codigo;

	private double creditos;

	@Column(length=45)
	private String curso;

	@Column(length=300)
	private String nome;

	private int periodo;

	@Column(length=4)
	private String tipo;

	@Column(name="VERSAO_GRADE", length=45)
	private String versaoGrade;

	public Grade() {
	}

	public String getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getCreditos() {
		return this.creditos;
	}

	public void setCreditos(double creditos) {
		this.creditos = creditos;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getVersaoGrade() {
		return this.versaoGrade;
	}

	public void setVersaoGrade(String versaoGrade) {
		this.versaoGrade = versaoGrade;
	}

}