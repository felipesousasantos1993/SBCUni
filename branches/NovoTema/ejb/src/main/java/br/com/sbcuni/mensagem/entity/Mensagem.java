package br.com.sbcuni.mensagem.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.sbcuni.usuario.entity.Usuario;

@Entity
public class Mensagem {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "titulo", length = 50, nullable = true)
	private String titulo;
	
	@Column(name = "texto", length = 1024, nullable = false)
	private String texto;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = Usuario.class)
	private Usuario remetente;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Usuario.class)
	private List<Usuario> destinatarios;
	
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = Anexo.class, mappedBy = "mensagem")
	private List<Anexo> anexos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}

	public List<Usuario> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<Usuario> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public List<Anexo> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<Anexo> anexos) {
		this.anexos = anexos;
	}
	
}
