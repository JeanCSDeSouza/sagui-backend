package br.com.unirio.sagui.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tutoria database table.
 * 
 */
@Entity
@Table(name="tutoria")
@NamedQuery(name="Tutoria.findAll", query="SELECT t FROM Tutoria t")
public class Tutoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TUTORIA_ID", unique=true, nullable=false)
	private String tutoriaId;

	@Column(name="PERIODO_TUTOR", nullable=false, length=4)
	private String periodoTutor;

	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TUTOR_USUARIO_FK", nullable=false)
	private Usuario usuario;

	public Tutoria() {
	}

	public String getTutoriaId() {
		return this.tutoriaId;
	}

	public void setTutoriaId(String tutoriaId) {
		this.tutoriaId = tutoriaId;
	}

	public String getPeriodoTutor() {
		return this.periodoTutor;
	}

	public void setPeriodoTutor(String periodoTutor) {
		this.periodoTutor = periodoTutor;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}