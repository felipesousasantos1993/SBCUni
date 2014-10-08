package br.com.sbcuni.inicio.bean;

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
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.grupoEstudo.service.GrupoEstudoSerivceBean;
import br.com.sbcuni.mensagem.entity.Mensagem;
import br.com.sbcuni.mensagem.service.MensagemServiceBean;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.topico.service.TopicoServiceBean;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;
import br.com.sbcuni.usuario.entity.Usuario;

@ViewScoped
@ManagedBean
public class PainelPrincipalBean extends GenericBean {

	private static final long serialVersionUID = -1683272201581494194L;

	public PainelPrincipalBean() {
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
	
	private List<Comentario> comentarios = new ArrayList<Comentario>();;
	private List<Topico> topicos = new ArrayList<Topico>();
	private List<Mensagem> mensagens = new ArrayList<Mensagem>();
	
	private Usuario usuarioSessao;
	
	@PostConstruct
	public void init() {
		usuarioSessao = UsuarioSessionBean.getInstance().getUsuarioSessao();
		comentarios.addAll(comentarioServiceBean.consultarComentariosPainel());
		topicos.addAll(topicoServiceBean.buscarTopicosPainel());
		mensagens.addAll(mensagemServiceBean.consultarMensagemPainel(usuarioSessao));
		if (Constantes.PERFIL_ALUNO.equals(usuarioSessao.getPerfil())) {
			for (GrupoEstudo ge : grupoEstudoSerivceBean.consultarGruposUsuario(usuarioSessao)) {
				comentarios.addAll(comentarioServiceBean.consultarComentariosPainelGrupos(ge));
				topicos.addAll(topicoServiceBean.buscarTopicosGrupo(ge));
			}
		} else {
			for (GrupoEstudo ge : grupoEstudoSerivceBean.consultarGruposProfessor(usuarioSessao)) {
				comentarios.addAll(comentarioServiceBean.consultarComentariosPainelGrupos(ge));
				topicos.addAll(topicoServiceBean.buscarTopicosGrupo(ge));
			}
		}
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

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
	
	
}
