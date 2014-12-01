package br.com.sbcuni.grupoEstudo;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.usuario.entity.Usuario;

@Entity
@NamedQueries({
	@NamedQuery(name = "GrupoEstudo.consultarGruposProfessor", query = "SELECT DISTINCT(ge) FROM GrupoEstudo ge LEFT JOIN FETCH ge.topicosGrupo JOIN FETCH ge.professor WHERE ge.professor.idUsuario =:idProfessor"),
	@NamedQuery(name = "GrupoEstudo.buscarGrupoEstudoId", query = "SELECT DISTINCT(ge) FROM GrupoEstudo ge LEFT JOIN FETCH ge.topicosGrupo JOIN FETCH ge.professor WHERE ge.idGrupoEstudo =:idGrupo "),
	@NamedQuery(name = "GrupoEstudo.pesquisa", query = "SELECT DISTINCT(ge) FROM GrupoEstudo ge LEFT JOIN FETCH ge.topicosGrupo JOIN FETCH ge.professor WHERE lower(ge.noGrupo) like :consulta OR ge.professor.nome like :consulta"),
	@NamedQuery(name = "GrupoEstudo.consultarGrupoTopico", query = "SELECT DISTINCT(ge) FROM GrupoEstudo ge JOIN FETCH ge.professor p JOIN FETCH ge.alunos WHERE :idTopico IN (SELECT t FROM Topico t WHERE t.grupoEstudo.idGrupoEstudo = ge.idGrupoEstudo)"),
	@NamedQuery(name = "GrupoEstudo.verificarUsuarioProfessorGrupo", query = "SELECT DISTINCT (ge) FROM GrupoEstudo ge WHERE ge.idGrupoEstudo =:idGrupoEstudo AND ge.professor.idUsuario =:idUsuario")
})
public class GrupoEstudo implements Serializable {

	private static final long serialVersionUID = 5749464765344848936L;

	@Id
	@GeneratedValue
	@Column(name = "idGrupoEstudo", nullable = false)
	private Long idGrupoEstudo;
	
	@Column(name = "noGrupo", length = 50, nullable = false)
	private String noGrupo;
	
	@Column(name = "dtCriacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCriacao;
	
	@Column(name = "dtUltimaAtualizacao", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtUltimaAtualizacao;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = Usuario.class)
	private Usuario professor;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = Usuario.class)
	private List<Usuario> alunos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Topico.class, mappedBy = "grupoEstudo")
	private List<Topico> topicosGrupo;
	
	@Transient
	private Long nuComentariosGrupo;
	
	@Transient
	private BigInteger nuAvaliacoesPositivas;
	
	@Transient
	private BigInteger nuAvaliacoesNegativas;
	
	@Transient
	private BigInteger nuTopicosAux; 
	
	@Transient
	private BigInteger nuComentarios;
	
	@Transient
	private BigInteger nuAlunos;

	public Long getIdGrupoEstudo() {
		return idGrupoEstudo;
	}

	public void setIdGrupoEstudo(Long idGrupoEstudo) {
		this.idGrupoEstudo = idGrupoEstudo;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Usuario getProfessor() {
		return professor;
	}

	public void setProfessor(Usuario professor) {
		this.professor = professor;
	}

	public List<Usuario> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Usuario> alunos) {
		this.alunos = alunos;
	}

	public List<Topico> getTopicosGrupo() {
		return topicosGrupo;
	}

	public void setTopicosGrupo(List<Topico> topicosGrupo) {
		this.topicosGrupo = topicosGrupo;
	}

	public String getNoGrupo() {
		return noGrupo;
	}

	public void setNoGrupo(String noGrupo) {
		this.noGrupo = noGrupo;
	}
	
	public Integer nuAlunos() {
		return alunos.size();
	}

	public Date getDtUltimaAtualizacao() {
		return dtUltimaAtualizacao;
	}

	public void setDtUltimaAtualizacao(Date dtUltimaAtualizacao) {
		this.dtUltimaAtualizacao = dtUltimaAtualizacao;
	}
	
	public Integer getNuTopicos() {
		return topicosGrupo.size();
	}

	public Long getNuComentariosGrupo() {
		return nuComentariosGrupo;
	}

	public void setNuComentariosGrupo(Long nuComentariosGrupo) {
		this.nuComentariosGrupo = nuComentariosGrupo;
	}

	public BigInteger getNuAvaliacoesPositivas() {
		return nuAvaliacoesPositivas;
	}

	public void setNuAvaliacoesPositivas(BigInteger nuAvaliacoesPositivas) {
		this.nuAvaliacoesPositivas = nuAvaliacoesPositivas;
	}

	public BigInteger getNuAvaliacoesNegativas() {
		return nuAvaliacoesNegativas;
	}

	public void setNuAvaliacoesNegativas(BigInteger nuAvaliacoesNegativas) {
		this.nuAvaliacoesNegativas = nuAvaliacoesNegativas;
	}

	public BigInteger getNuComentarios() {
		return nuComentarios;
	}

	public void setNuComentarios(BigInteger nuComentarios) {
		this.nuComentarios = nuComentarios;
	}

	public BigInteger getNuTopicosAux() {
		return nuTopicosAux;
	}

	public void setNuTopicosAux(BigInteger nuTopicosAux) {
		this.nuTopicosAux = nuTopicosAux;
	}

	public BigInteger getNuAlunos() {
		return nuAlunos;
	}

	public void setNuAlunos(BigInteger nuAlunos) {
		this.nuAlunos = nuAlunos;
	}
}