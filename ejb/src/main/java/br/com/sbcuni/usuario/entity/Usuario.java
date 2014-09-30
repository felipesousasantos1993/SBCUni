package br.com.sbcuni.usuario.entity;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.sbcuni.comentario.entity.Comentario;
import br.com.sbcuni.mensagem.entity.Mensagem;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.topico.entity.Topico;

/**
 * Nome : Usuario 
 * Objetivo: Conter as informações da tabela Usuario.
 * @since : Data de criação 28/04/2014 13:29:50
 * @author Felipe de Sousa Santos
 * @version $Revision: 1.0 $
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "Usuario.buscarTodos", query = "SELECT u FROM Usuario u"),
	@NamedQuery(name = "Usuario.buscarPorPerfil", query = "SELECT u FROM Usuario u WHERE u.perfil =:perfil"),
	@NamedQuery(name = "Usuario.consultarUsuarioLogin", query = "SELECT u FROM Usuario u WHERE u.matricula =:matricula AND u.senha =:senha"),
	@NamedQuery(name = "Usuario.consultarAlunoNomeOuMatricula", query = "SELECT u FROM Usuario u WHERE lower(u.nome) like :nome OR u.matricula like :matricula AND u.perfil = 1"),
	@NamedQuery(name = "Usuario.consultarAlunoNomeOuEmail", query = "SELECT u FROM Usuario u WHERE lower(u.nome) like :nome OR lower(u.email) like :matricula")
})
public class Usuario implements Serializable {

	private static final long serialVersionUID = 2367053252703641904L;

	@Id
	@GeneratedValue
	@Column(name = "idUsuario", length = 5, nullable = false)
	private Long idUsuario;

	@Column(name = "nome", length = 58, nullable = false)
	private String nome;
	
	@Column(name = "cpf", length = 11, nullable = false)
	private String cpf;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@Column(name = "matricula", length = 13, nullable = false)
	private String matricula;

	@Column(name = "senha", length = 8, nullable = true)
	private String senha;
	
	@Column(name = "perfil", length = 1, nullable = false)
	private Integer perfil;
	
	@Column(name = "status", nullable = false)
	private Boolean status;
	
	@Column(name = "dtCadastro", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCadastro;
	
	@Column(name = "telFixo", nullable = true)
	private String telFixo;
	
	@Column(name = "telCelular", nullable = true)
	private String telCelular;
	
	@Column(name = "cidade", nullable = true)
	private String cidade;
	
	@Column(name = "estado", nullable = true)
	private String estado;
	
	@Column(name = "sobreMim", length = 255, nullable = true)
	private String sobreMim;
	
	@Column(name = "dtUltimoAcesso", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtUltimoAcesso;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario" , targetEntity = Topico.class)
	private List<Topico> topicos;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario", targetEntity = Comentario.class)
	private List<Comentario> comentarios;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "remetente", targetEntity = Mensagem.class)
	private List<Mensagem> msgsEnviadas;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = Mensagem.class, mappedBy = "destinatarios")
	private List<Mensagem> msgsRecebidas;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = GrupoEstudo.class, mappedBy = "alunos")
	private List<GrupoEstudo> grupos;
	
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = GrupoEstudo.class, mappedBy = "professor")
	private List<GrupoEstudo> gruposEstudos;
	
	@Transient
	private Boolean marcado = Boolean.FALSE;
	
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getMarcado() {
		return marcado;
	}

	public void setMarcado(Boolean marcado) {
		this.marcado = marcado;
	}
	
	public String getDePerfil() {
		if (perfil.equals(1)) {
			return "Aluno";
		}
		if (perfil.equals(2)) {
			return "Professor";
		}
		if (perfil.equals(3)) {
			return "Coordenador";
		}
		return null;
	}
	
	public String getDeStatus() {
		if (status) {
			return "Ativo";
		} else {
			return "Inativo";
		}
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	public Date getDtUltimoAcesso() {
		return dtUltimoAcesso;
	}

	public void setDtUltimoAcesso(Date dtUltimoAcesso) {
		this.dtUltimoAcesso = dtUltimoAcesso;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Mensagem> getMsgsEnviadas() {
		return msgsEnviadas;
	}

	public void setMsgsEnviadas(List<Mensagem> msgsEnviadas) {
		this.msgsEnviadas = msgsEnviadas;
	}

	public List<Mensagem> getMsgsRecebidas() {
		return msgsRecebidas;
	}

	public void setMsgsRecebidas(List<Mensagem> msgsRecebidas) {
		this.msgsRecebidas = msgsRecebidas;
	}

	public List<GrupoEstudo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoEstudo> grupos) {
		this.grupos = grupos;
	}

	public List<GrupoEstudo> getGruposEstudos() {
		return gruposEstudos;
	}

	public void setGruposEstudos(List<GrupoEstudo> gruposEstudos) {
		this.gruposEstudos = gruposEstudos;
	}

	public String getMatriculaNomeAluno() {
		return matricula.concat(" - ").concat(nome);
	}

	public String getTelFixo() {
		return telFixo;
	}

	public void setTelFixo(String telFixo) {
		this.telFixo = telFixo;
	}

	public String getTelCelular() {
		return telCelular;
	}

	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSobreMim() {
		return sobreMim;
	}

	public void setSobreMim(String sobreMim) {
		this.sobreMim = sobreMim;
	}

}