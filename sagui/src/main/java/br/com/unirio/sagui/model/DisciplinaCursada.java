package br.com.unirio.sagui.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the disciplina_cursada database table.
 * 
 */
@Entity
@Table(name="disciplina_cursada")
@NamedQuery(name="DisciplinaCursada.findAll", query="SELECT d FROM DisciplinaCursada d")
public class DisciplinaCursada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private String disciplina_Cursada_Id;

	@Column(nullable=false, length=45)
	private String codigo;

	private double creditos;

	@Column(length=45)
	private String curso;

	private double nota;

	private int periodo;

	@Column(name="QTD_CURSADA", length=45)
	private short qtdCursada;

	@Column(name="QTD_REPROVACAO", length=45)
	private short qtdReprovacao;

	@Column(length=45)
	private String situacao;

	@Column(length=4)
	private String tipo;

	@Column(length=300)
	private String titulo;

	@Column(name="VERSAO_GRADE", nullable=false, length=45)
	private String versaoGrade;

	//bi-directional many-to-one association to Aluno
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DC_ALUNO_FK", nullable=false)
	private Aluno aluno;

	public DisciplinaCursada() {
	}

	public String getDisciplina_Cursada_Id() {
		return this.disciplina_Cursada_Id;
	}

	public void setDisciplina_Cursada_Id(String disciplina_Cursada_Id) {
		this.disciplina_Cursada_Id = disciplina_Cursada_Id;
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

	public double getNota() {
		return this.nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public int getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	public short getQtdCursada() {
		return this.qtdCursada;
	}

	public void setQtdCursada(short qtdCursada) {
		this.qtdCursada = qtdCursada;
	}

	public short getQtdReprovacao() {
		return this.qtdReprovacao;
	}

	public void setQtdReprovacao(short qtdReprovacao) {
		this.qtdReprovacao = qtdReprovacao;
	}

	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getVersaoGrade() {
		return this.versaoGrade;
	}

	public void setVersaoGrade(String versaoGrade) {
		this.versaoGrade = versaoGrade;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}