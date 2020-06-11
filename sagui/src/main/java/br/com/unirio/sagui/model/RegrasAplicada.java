package br.com.unirio.sagui.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the regras_aplicadas database table.
 * 
 */
@Entity
@Table(name="regras_aplicadas")
@NamedQuery(name="RegrasAplicada.findAll", query="SELECT r FROM RegrasAplicada r")
public class RegrasAplicada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="REGRAS_A_ID", unique=true, nullable=false)
	private String regrasAId;

	@Column(name="CONCLUIR_NO_TEMPO", length=3)
	private String concluirNoTempo;

	@Column(name="CR_MAIOR_QUE_4", nullable=false, length=3)
	private String crMaiorQue4;

	@Column(name="CR_PLANO_I_RESPECTED", length=3)
	private String crPlanoIRespected;

	@Column(length=3)
	private String jubilamento;

	@Column(name="NESSESSITA_PLANO_I", nullable=false, length=3)
	private String nessessitaPlanoI;

	@Column(name="QTD_REPROVACOES", length=3)
	private String qtdReprovacoes;

	//bi-directional many-to-one association to Aluno
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="REGRAS_A_ALUNO_FK", nullable=false)
	private Aluno aluno;

	public RegrasAplicada() {
	}

	public String getRegrasAId() {
		return this.regrasAId;
	}

	public void setRegrasAId(String regrasAId) {
		this.regrasAId = regrasAId;
	}

	public String getConcluirNoTempo() {
		return this.concluirNoTempo;
	}

	public void setConcluirNoTempo(String concluirNoTempo) {
		this.concluirNoTempo = concluirNoTempo;
	}

	public String getCrMaiorQue4() {
		return this.crMaiorQue4;
	}

	public void setCrMaiorQue4(String crMaiorQue4) {
		this.crMaiorQue4 = crMaiorQue4;
	}

	public String getCrPlanoIRespected() {
		return this.crPlanoIRespected;
	}

	public void setCrPlanoIRespected(String crPlanoIRespected) {
		this.crPlanoIRespected = crPlanoIRespected;
	}

	public String getJubilamento() {
		return this.jubilamento;
	}

	public void setJubilamento(String jubilamento) {
		this.jubilamento = jubilamento;
	}

	public String getNessessitaPlanoI() {
		return this.nessessitaPlanoI;
	}

	public void setNessessitaPlanoI(String nessessitaPlanoI) {
		this.nessessitaPlanoI = nessessitaPlanoI;
	}

	public String getQtdReprovacoes() {
		return this.qtdReprovacoes;
	}

	public void setQtdReprovacoes(String qtdReprovacoes) {
		this.qtdReprovacoes = qtdReprovacoes;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}