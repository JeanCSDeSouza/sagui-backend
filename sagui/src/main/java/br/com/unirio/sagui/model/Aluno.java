package br.com.unirio.sagui.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the aluno database table.
 * 
 */
@Entity
@Table(name="aluno")
@NamedQuery(name="Aluno.findAll", query="SELECT a FROM Aluno a")
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private String aluno_Id;

	private double cra;

	@Column(nullable=false, length=10)
	private String curso;

	@Column(length=300)
	private String email;

	@Column(nullable=false, length=5)
	private String entrada_Ano_Periodo;

	@Column(nullable=false, length=45)
	private String matricula;

	@Column(name="NOME_ALUNO", length=300)
	private String nome;

	@Column(name="VERSAO_GRADE", length=45)
	private String versaoGrade;

	//bi-directional many-to-one association to DisciplinaCursada
	@OneToMany(mappedBy="aluno", cascade = CascadeType.ALL)
	private Set<DisciplinaCursada> disciplinaCursadas;

	//bi-directional many-to-one association to ConfirmacaoMatricula
	@OneToMany(mappedBy="aluno")
	private Set<ConfirmacaoMatricula> confirmacaoMatriculas;

	//bi-directional many-to-one association to CrPeriodizado
	@OneToMany(mappedBy="aluno", cascade = CascadeType.ALL)
	private Set<CrPeriodizado> crPeriodizados;

	//bi-directional many-to-one association to PlanoDeIntegralizacao
	@OneToMany(mappedBy="aluno")
	private Set<PlanoDeIntegralizacao> planoDeIntegralizacaos;

	//bi-directional many-to-one association to RegrasAplicada
	@OneToMany(mappedBy="aluno")
	private Set<RegrasAplicada> regrasAplicadas;

	public Aluno() {
	}

	public String getAluno_Id() {
		return this.aluno_Id;
	}

	public void setAluno_Id(String aluno_Id) {
		this.aluno_Id = aluno_Id;
	}

	public double getCra() {
		return this.cra;
	}

	public void setCra(double cra) {
		this.cra = cra;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEntrada_Ano_Periodo() {
		return this.entrada_Ano_Periodo;
	}

	public void setEntrada_Ano_Periodo(String entrada_Ano_Periodo) {
		this.entrada_Ano_Periodo = entrada_Ano_Periodo;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVersaoGrade() {
		return this.versaoGrade;
	}

	public void setVersaoGrade(String versaoGrade) {
		this.versaoGrade = versaoGrade;
	}

	public Set<DisciplinaCursada> getDisciplinaCursadas() {
		return this.disciplinaCursadas;
	}

	public void setDisciplinaCursadas(Set<DisciplinaCursada> disciplinaCursadas) {
		this.disciplinaCursadas = disciplinaCursadas;
	}

	public DisciplinaCursada addDisciplinaCursada(DisciplinaCursada disciplinaCursada) {
		getDisciplinaCursadas().add(disciplinaCursada);
		disciplinaCursada.setAluno(this);

		return disciplinaCursada;
	}

	public DisciplinaCursada removeDisciplinaCursada(DisciplinaCursada disciplinaCursada) {
		getDisciplinaCursadas().remove(disciplinaCursada);
		disciplinaCursada.setAluno(null);

		return disciplinaCursada;
	}

	public Set<ConfirmacaoMatricula> getConfirmacaoMatriculas() {
		return this.confirmacaoMatriculas;
	}

	public void setConfirmacaoMatriculas(Set<ConfirmacaoMatricula> confirmacaoMatriculas) {
		this.confirmacaoMatriculas = confirmacaoMatriculas;
	}

	public ConfirmacaoMatricula addConfirmacaoMatricula(ConfirmacaoMatricula confirmacaoMatricula) {
		getConfirmacaoMatriculas().add(confirmacaoMatricula);
		confirmacaoMatricula.setAluno(this);

		return confirmacaoMatricula;
	}

	public ConfirmacaoMatricula removeConfirmacaoMatricula(ConfirmacaoMatricula confirmacaoMatricula) {
		getConfirmacaoMatriculas().remove(confirmacaoMatricula);
		confirmacaoMatricula.setAluno(null);

		return confirmacaoMatricula;
	}

	public Set<CrPeriodizado> getCrPeriodizados() {
		return this.crPeriodizados;
	}

	public void setCrPeriodizados(Set<CrPeriodizado> crPeriodizados) {
		this.crPeriodizados = crPeriodizados;
	}

	public CrPeriodizado addCrPeriodizado(CrPeriodizado crPeriodizado) {
		getCrPeriodizados().add(crPeriodizado);
		crPeriodizado.setAluno(this);

		return crPeriodizado;
	}

	public CrPeriodizado removeCrPeriodizado(CrPeriodizado crPeriodizado) {
		getCrPeriodizados().remove(crPeriodizado);
		crPeriodizado.setAluno(null);

		return crPeriodizado;
	}

	public Set<PlanoDeIntegralizacao> getPlanoDeIntegralizacaos() {
		return this.planoDeIntegralizacaos;
	}

	public void setPlanoDeIntegralizacaos(Set<PlanoDeIntegralizacao> planoDeIntegralizacaos) {
		this.planoDeIntegralizacaos = planoDeIntegralizacaos;
	}

	public PlanoDeIntegralizacao addPlanoDeIntegralizacao(PlanoDeIntegralizacao planoDeIntegralizacao) {
		getPlanoDeIntegralizacaos().add(planoDeIntegralizacao);
		planoDeIntegralizacao.setAluno(this);

		return planoDeIntegralizacao;
	}

	public PlanoDeIntegralizacao removePlanoDeIntegralizacao(PlanoDeIntegralizacao planoDeIntegralizacao) {
		getPlanoDeIntegralizacaos().remove(planoDeIntegralizacao);
		planoDeIntegralizacao.setAluno(null);

		return planoDeIntegralizacao;
	}

	public Set<RegrasAplicada> getRegrasAplicadas() {
		return this.regrasAplicadas;
	}

	public void setRegrasAplicadas(Set<RegrasAplicada> regrasAplicadas) {
		this.regrasAplicadas = regrasAplicadas;
	}

	public RegrasAplicada addRegrasAplicada(RegrasAplicada regrasAplicada) {
		getRegrasAplicadas().add(regrasAplicada);
		regrasAplicada.setAluno(this);

		return regrasAplicada;
	}

	public RegrasAplicada removeRegrasAplicada(RegrasAplicada regrasAplicada) {
		getRegrasAplicadas().remove(regrasAplicada);
		regrasAplicada.setAluno(null);

		return regrasAplicada;
	}

}