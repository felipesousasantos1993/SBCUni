package br.com.sbcuni.comentario.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import br.com.sbcuni.avaliacao.entity.Avaliacao;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.Util;

@Entity
@NamedQueries({
	@NamedQuery(name = "Comentario.consultarComentariosTopico", query = "SELECT c FROM Comentario c JOIN FETCH c.usuario WHERE c.topico.idTopico =:idTopico ORDER BY c.dtCriacao ASC"),
	@NamedQuery(name = "Comentario.consultarNuComentariosUsuarioGrupoEstudo", query = "SELECT count(c) FROM Comentario c WHERE c.usuario.idUsuario =:idUsuario AND c.topico.grupoEstudo.idGrupoEstudo =:idGrupoEstudo")
})
public class Comentario implements Serializable {

	private static final long serialVersionUID = -8746442475735810459L;

	@Id
	@GeneratedValue
	@Column(name = "idComentario", length = 5, nullable = false)
	private Long idComentario;

	@Column(name = "descricao", length = 2048, nullable = false)
	private String descricao;
	
	@Column(name = "dtCriacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCriacao;

	@Column(name = "dtUltimaAtualizacao", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtUltimaAtualizacao;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = Topico.class)
	@JoinColumn(name = "topico", referencedColumnName = "idTopico")
	private Topico topico;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "comentarios", targetEntity = Avaliacao.class)
	private List<Avaliacao> avaliacoes;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = Usuario.class)
	@JoinColumn(name = "usuario", referencedColumnName = "idUsuario")
	private Usuario usuario;
	
	@Transient
	private BigInteger nuAvaliacaoPositivas;
	
	@Transient
	private BigInteger nuAvaliacaoNegativas;
	
	
	@Transient
	private Avaliacao avaliacaoUsuario;

	public Long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}

	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Avaliacao getAvaliacaoUsuario() {
		return avaliacaoUsuario;
	}

	public void setAvaliacaoUsuario(Avaliacao avaliacaoUsuario) {
		this.avaliacaoUsuario = avaliacaoUsuario;
	}

	public BigInteger getNuAvaliacaoPositivas() {
		return nuAvaliacaoPositivas;
	}

	public void setNuAvaliacaoPositivas(BigInteger nuAvaliacaoPositivas) {
		this.nuAvaliacaoPositivas = nuAvaliacaoPositivas;
	}

	public BigInteger getNuAvaliacaoNegativas() {
		return nuAvaliacaoNegativas;
	}

	public void setNuAvaliacaoNegativas(BigInteger nuAvaliacaoNegativas) {
		this.nuAvaliacaoNegativas = nuAvaliacaoNegativas;
	}

	public Date getDtUltimaAtualizacao() {
		return dtUltimaAtualizacao;
	}

	public void setDtUltimaAtualizacao(Date dtUltimaAtualizacao) {
		this.dtUltimaAtualizacao = dtUltimaAtualizacao;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getTempoComentario() {
		return "Atualizado " + Util.getDiferencaTempo(new DateTime(getDtUltimaAtualizacao()));
	}
	
	public String getTempoComentarioCriacao() {
		return Util.getDiferencaTempo(new DateTime(getDtCriacao()));
	}
}