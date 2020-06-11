package br.com.unirio.sagui.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the confirmacao_matricula database table.
 * 
 */
@Entity
@Table(name="confirmacao_matricula")
@NamedQuery(name="ConfirmacaoMatricula.findAll", query="SELECT c FROM ConfirmacaoMatricula c")
public class ConfirmacaoMatricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CONFIRMACAO_MATRICULA_ID", unique=true, nullable=false)
	private String confirmacaoMatriculaId;

	@Column(name="CONF_MAT_TEXT", length=3200)
	private String confMatText;

	//bi-directional many-to-one association to Aluno
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CM_ALUNO_FK")
	private Aluno aluno;

	public ConfirmacaoMatricula() {
	}

	public String getConfirmacaoMatriculaId() {
		return this.confirmacaoMatriculaId;
	}

	public void setConfirmacaoMatriculaId(String confirmacaoMatriculaId) {
		this.confirmacaoMatriculaId = confirmacaoMatriculaId;
	}

	public String getConfMatText() {
		return this.confMatText;
	}

	public void setConfMatText(String confMatText) {
		this.confMatText = confMatText;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}