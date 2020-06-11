package br.com.unirio.sagui.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USUARIO_ID", unique=true, nullable=false)
	private String usuarioId;

	@Column(length=300)
	private String email;

	@Column(length=300)
	private String nome;

	@Column(name="OAUTH_KEY", length=500)
	private String oauthKey;

	@Column(length=45)
	private String role;

	@Column(length=16)
	private String senha;

	//bi-directional many-to-one association to Tutoria
	@OneToMany(mappedBy="usuario")
	private Set<Tutoria> tutorias;

	public Usuario() {
	}

	public String getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getOauthKey() {
		return this.oauthKey;
	}

	public void setOauthKey(String oauthKey) {
		this.oauthKey = oauthKey;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Tutoria> getTutorias() {
		return this.tutorias;
	}

	public void setTutorias(Set<Tutoria> tutorias) {
		this.tutorias = tutorias;
	}

	public Tutoria addTutoria(Tutoria tutoria) {
		getTutorias().add(tutoria);
		tutoria.setUsuario(this);

		return tutoria;
	}

	public Tutoria removeTutoria(Tutoria tutoria) {
		getTutorias().remove(tutoria);
		tutoria.setUsuario(null);

		return tutoria;
	}

}