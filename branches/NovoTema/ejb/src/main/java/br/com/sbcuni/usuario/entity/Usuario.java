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
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.mensagem.entity.Mensagem;
import br.com.sbcuni.topico.entity.Topico;

/**
 * Nome : Usuario Objetivo: Conter as informações da tabela Usuario.
 * 
 * @since : Data de criação 28/04/2014 13:29:50
 * @author Felipe de Sousa Santos
 * @version $Revision: 1.0 $
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Usuario.buscarTodos", query = "SELECT u FROM Usuario u"), 
	@NamedQuery(name = "Usuario.buscarPorPerfil", query = "SELECT u FROM Usuario u WHERE u.perfil =:perfil"),
	@NamedQuery(name = "Usuario.consultarUsuarioLogin", query = "SELECT u FROM Usuario u WHERE u.matricula =:matricula AND u.senha =:senha"),
	@NamedQuery(name = "Usuario.consultarAlunoNomeOuMatricula", query = "SELECT u FROM Usuario u WHERE (lower(u.nome) like :nome OR u.matricula like :matricula) AND u.perfil = 1"),
	@NamedQuery(name = "Usuario.consultarAlunoNomeOuEmail", query = "SELECT u FROM Usuario u WHERE lower(u.nome) like :nome OR lower(u.email) like :matricula"),
	@NamedQuery(name = "Usuario.pesquisa", query = "SELECT u FROM Usuario u WHERE lower(u.nome) like :nome OR lower(u.email) like :email OR lower(u.matricula) like :matricula"),
	@NamedQuery(name = "Usuario.consultarPorMatriculaCpf", query = "SELECT u FROM Usuario u WHERE u.cpf =:cpf AND u.matricula =:matricula"),
	@NamedQuery(name = "Usuario.consultarPorMatriculaCpfEmail", query = "SELECT u FROM Usuario u WHERE u.cpf =:cpf OR u.matricula =:matricula OR u.email =:email")
})
public class Usuario implements Serializable {

	private static final long serialVersionUID = 2367053252703641904L;

	@Id
	@GeneratedValue
	@Column(name = "idUsuario", length = 5, nullable = false)
	private Long idUsuario;

	@Column(name = "nome", length = 58, nullable = false)
	private String nome;

	@Column(name = "cpf", length = 11, nullable = true)
	private String cpf;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@Column(name = "matricula", length = 13, nullable = false)
	private String matricula;

	@Column(name = "senha", length = 8, nullable = true)
	private String senha;

	@Column(name = "perfil", length = 1, nullable = true)
	private Integer perfil;

	@Column(name = "dtCadastro", nullable = true)
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

	@Column(name = "avatar", nullable = true)
	private String avatar;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario", targetEntity = Topico.class)
	private List<Topico> topicos;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usuario", targetEntity = Comentario.class)
	private List<Comentario> comentarios;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "remetente", targetEntity = Mensagem.class)
	private List<Mensagem> msgsEnviadas;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = Mensagem.class, mappedBy = "destinatario")
	private List<Mensagem> msgsRecebidas;

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = GrupoEstudo.class, mappedBy = "alunos")
	private List<GrupoEstudo> grupos;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = GrupoEstudo.class, mappedBy = "professor")
	private List<GrupoEstudo> gruposEstudos;

	@Transient
	private Boolean marcado = Boolean.FALSE;

	@Transient
	private Long nuComentariosNoGrupo;
	
	@Transient
	private Boolean pertenceGrupo;
	
	@Transient
	private Integer indice;

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

	public Boolean getMarcado() {
		return marcado;
	}

	public void setMarcado(Boolean marcado) {
		this.marcado = marcado;
	}

	public String getDePerfil() {
		switch (perfil) {
		case 1:
			return "Aluno";
		case 2:
			return "Professor";
		case 3:
			return "Coordenador";
		default:
			return "Administrador";
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

	public Integer getNuTopicos() {
		return getTopicos().size();
	}

	public Long getNuComentariosNoGrupo() {
		return nuComentariosNoGrupo;
	}

	public void setNuComentariosNoGrupo(Long nuComentariosNoGrupo) {
		this.nuComentariosNoGrupo = nuComentariosNoGrupo;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((dtCadastro == null) ? 0 : dtCadastro.hashCode());
		result = prime * result + ((dtUltimoAcesso == null) ? 0 : dtUltimoAcesso.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((sobreMim == null) ? 0 : sobreMim.hashCode());
		result = prime * result + ((telCelular == null) ? 0 : telCelular.hashCode());
		result = prime * result + ((telFixo == null) ? 0 : telFixo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dtCadastro == null) {
			if (other.dtCadastro != null)
				return false;
		} else if (!dtCadastro.equals(other.dtCadastro))
			return false;
		if (dtUltimoAcesso == null) {
			if (other.dtUltimoAcesso != null)
				return false;
		} else if (!dtUltimoAcesso.equals(other.dtUltimoAcesso))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (perfil == null) {
			if (other.perfil != null)
				return false;
		} else if (!perfil.equals(other.perfil))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (sobreMim == null) {
			if (other.sobreMim != null)
				return false;
		} else if (!sobreMim.equals(other.sobreMim))
			return false;
		if (telCelular == null) {
			if (other.telCelular != null)
				return false;
		} else if (!telCelular.equals(other.telCelular))
			return false;
		if (telFixo == null) {
			if (other.telFixo != null)
				return false;
		} else if (!telFixo.equals(other.telFixo))
			return false;
		return true;
	}

	public Boolean getPertenceGrupo() {
		return pertenceGrupo;
	}

	public void setPertenceGrupo(Boolean pertenceGrupo) {
		this.pertenceGrupo = pertenceGrupo;
	}

	public Integer getIndice() {
		return indice;
	}

	public void setIndice(Integer indice) {
		this.indice = indice;
	}
}