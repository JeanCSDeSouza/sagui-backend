package br.com.unirio.sagui.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the plano_de_integralizacao database table.
 * 
 */
@Entity
@Table(name="plano_de_integralizacao")
@NamedQuery(name="PlanoDeIntegralizacao.findAll", query="SELECT p FROM PlanoDeIntegralizacao p")
public class PlanoDeIntegralizacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PLANO_DE_INTEGRALIZACAO_ID", unique=true, nullable=false)
	private String planoDeIntegralizacaoId;

	@Temporal(TemporalType.DATE)
	@Column(name="DATA_ENVIO")
	private Date dataEnvio;

	@Column(name="PLANO_TEXT", length=3200)
	private String planoText;

	//bi-directional many-to-one association to Aluno
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PI_ALUNO_FK")
	private Aluno aluno;

	public PlanoDeIntegralizacao() {
	}

	public String getPlanoDeIntegralizacaoId() {
		return this.planoDeIntegralizacaoId;
	}

	public void setPlanoDeIntegralizacaoId(String planoDeIntegralizacaoId) {
		this.planoDeIntegralizacaoId = planoDeIntegralizacaoId;
	}

	public Date getDataEnvio() {
		return this.dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getPlanoText() {
		return this.planoText;
	}

	public void setPlanoText(String planoText) {
		this.planoText = planoText;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}