package br.com.sbcuni.grupoEstudo;

import java.io.Serializable;
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

import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.usuario.entity.Usuario;

@Entity
@NamedQueries({
	@NamedQuery(name = "GrupoEstudo.consultarGruposProfessor", query = "SELECT DISTINCT(ge) FROM GrupoEstudo ge LEFT JOIN FETCH ge.topicosGrupo JOIN FETCH ge.professor WHERE ge.professor.idUsuario =:idProfessor"),
	@NamedQuery(name = "GrupoEstudo.buscarGrupoEstudoId", query = "SELECT DISTINCT(ge) FROM GrupoEstudo ge LEFT JOIN FETCH ge.topicosGrupo JOIN FETCH ge.professor WHERE ge.idGrupoEstudo =:idGrupo ")
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
}
