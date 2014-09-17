package br.com.sbcuni.avaliacao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;

import br.com.sbcuni.comentario.entity.Comentario;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.usuario.entity.Usuario;

@Entity
@NamedQueries({
})
public class Avaliacao implements Serializable {

	private static final long serialVersionUID = -6084790444167251537L;

	@Id
	@GeneratedValue
	@Column(name = "idAvaliacao", nullable = false)
	private Long idAvaliacao;
	
	@Column(name = "avaliacao", nullable = true) 
	private Boolean avaliacao;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = Comentario.class)
	private List<Comentario> comentarios;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,targetEntity = Topico.class)
	private List<Topico> topicos;
 	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = Usuario.class)
	@JoinColumn(name = "usuario", referencedColumnName = "idUsuario")
	private Usuario usuario;

	public Boolean getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Boolean avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(Long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
	}

}