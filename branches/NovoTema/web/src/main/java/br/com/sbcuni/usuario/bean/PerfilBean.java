package br.com.sbcuni.usuario.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.comentario.entity.Comentario;
import br.com.sbcuni.comentario.service.ComentarioServiceBean;
import br.com.sbcuni.constantes.Constantes;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.grupoEstudo.service.GrupoEstudoSerivceBean;
import br.com.sbcuni.mensagem.entity.Mensagem;
import br.com.sbcuni.mensagem.service.MensagemServiceBean;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.topico.service.TopicoServiceBean;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.WebResources;

@ViewScoped
@ManagedBean
public class PerfilBean extends GenericBean {

	private static final long serialVersionUID = 6740214399442770330L;

	public PerfilBean() {
		super();
	}
	@EJB
	private GrupoEstudoSerivceBean grupoEstudoSerivceBean;
	@EJB
	private ComentarioServiceBean comentarioServiceBean;
	@EJB
	private TopicoServiceBean topicoServiceBean;
	@EJB
	private MensagemServiceBean mensagemServiceBean;
	
	private Long nuTopicos;
	private Long nuComentarios;
	
	private List<Topico> topicos = new ArrayList<Topico>();
	private List<Comentario> comentarios = new ArrayList<Comentario>();
	private List<Mensagem> mensagens = new ArrayList<Mensagem>();
	private List<GrupoEstudo> grupoEstudos;

	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		usuario = (Usuario) WebResources.getFlash().get(WebResources.USUARIO);
		if (Constantes.PERFIL_ALUNO.equals(usuario.getPerfil())) {
			grupoEstudos = grupoEstudoSerivceBean.consultarGruposUsuario(usuario);
		} else {
			grupoEstudos = grupoEstudoSerivceBean.consultarGruposProfessor(usuario);
		}
		topicos = topicoServiceBean.buscarTopicoPorUsuario(usuario.getIdUsuario());
		comentarios = comentarioServiceBean.consultarComentarioUsuario(usuario, 20);
		nuComentarios = comentarioServiceBean.consultarNuComentarioUsuario(usuario);
		mensagens = mensagemServiceBean.consultarRecebidas(usuario, 20);
		nuTopicos = topicoServiceBean.buscarNuTopicosUsuario(usuario);
	}
	
	public String telaMudarSenha() {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.MUDAR_SENHA_PATH;
	}
	

	public List<GrupoEstudo> getGrupoEstudos() {
		return grupoEstudos;
	}

	public void setGrupoEstudos(List<GrupoEstudo> grupoEstudos) {
		this.grupoEstudos = grupoEstudos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getNuTopicos() {
		return nuTopicos;
	}

	public void setNuTopicos(Long nuTopicos) {
		this.nuTopicos = nuTopicos;
	}

	public Long getNuComentarios() {
		return nuComentarios;
	}

	public void setNuComentarios(Long nuComentarios) {
		this.nuComentarios = nuComentarios;
	}

	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
	
}
