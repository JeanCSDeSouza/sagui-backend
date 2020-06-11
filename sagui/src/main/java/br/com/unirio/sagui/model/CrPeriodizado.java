package br.com.unirio.sagui.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cr_periodizado database table.
 * 
 */
@Entity
@Table(name="cr_periodizado")
@NamedQuery(name="CrPeriodizado.findAll", query="SELECT c FROM CrPeriodizado c")
public class CrPeriodizado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CR_PERIODIZADO_ID", unique=true, nullable=false)
	private String crPeriodizadoId;

	@Column(name="CARGA_CREDITOS", length=5)
	private String cargaCreditos;

	@Column(name="CARGA_HORARIA", length=5)
	private String cargaHoraria;

	@Column(nullable=false)
	private double cr;

	@Column(nullable=false, length=22)
	private String periodo;

	//bi-directional many-to-one association to Aluno
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CR_P_ALUNO_FK", nullable=false)
	private Aluno aluno;

	public CrPeriodizado() {
	}

	public String getCrPeriodizadoId() {
		return this.crPeriodizadoId;
	}

	public void setCrPeriodizadoId(String crPeriodizadoId) {
		this.crPeriodizadoId = crPeriodizadoId;
	}

	public String getCargaCreditos() {
		return this.cargaCreditos;
	}

	public void setCargaCreditos(String cargaCreditos) {
		this.cargaCreditos = cargaCreditos;
	}

	public String getCargaHoraria() {
		return this.cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public double getCr() {
		return this.cr;
	}

	public void setCr(double cr) {
		this.cr = cr;
	}

	public String getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}