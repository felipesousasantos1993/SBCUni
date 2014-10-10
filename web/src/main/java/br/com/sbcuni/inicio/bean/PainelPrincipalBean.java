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
	private List<Topico> topicosMaisVisualizados = new ArrayList<Topico>();
	private List<Topico> topicosMaisAvaliadosPostivo = new ArrayList<Topico>();
	private List<Topico> topicosMaisAvaliadosNegativo = new ArrayList<Topico>();
	private List<Mensagem> mensagens = new ArrayList<Mensagem>();
	private List<GrupoEstudo> gruposEstudo = new ArrayList<GrupoEstudo>();
	
	private List<Long> listaIdGrupo = new ArrayList<Long>();

	private Usuario usuarioSessao;

	@PostConstruct
	public void init() {
		usuarioSessao = UsuarioSessionBean.getInstance().getUsuarioSessao();
		if (Constantes.PERFIL_ALUNO.equals(usuarioSessao.getPerfil())) {
			gruposEstudo = grupoEstudoSerivceBean.consultarGruposUsuario(usuarioSessao);
		} else {
			gruposEstudo = grupoEstudoSerivceBean.consultarGruposProfessor(usuarioSessao);
		}
		mensagens.addAll(mensagemServiceBean.consultarMensagemPainel(usuarioSessao));
		for (GrupoEstudo ge : gruposEstudo) {
			listaIdGrupo.add(ge.getIdGrupoEstudo());
		}
		comentarios.addAll(comentarioServiceBean.consultarComentariosPainel(listaIdGrupo));
		topicos.addAll(topicoServiceBean.buscarTopicosPainel(listaIdGrupo));
		topicosMaisVisualizados.addAll(topicoServiceBean.buscarTopicosMaisVisualizados(listaIdGrupo));
		topicosMaisAvaliadosPostivo.addAll(topicoServiceBean.buscarTopicosMaisBemAvaliados());
		topicosMaisAvaliadosNegativo.addAll(topicoServiceBean.buscarTopicosMaisMalAvaliados());
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

	public List<GrupoEstudo> getGruposEstudo() {
		return gruposEstudo;
	}

	public void setGruposEstudo(List<GrupoEstudo> gruposEstudo) {
		this.gruposEstudo = gruposEstudo;
	}

	public List<Topico> getTopicosMaisVisualizados() {
		return topicosMaisVisualizados;
	}

	public void setTopicosMaisVisualizados(List<Topico> topicosMaisVisualizados) {
		this.topicosMaisVisualizados = topicosMaisVisualizados;
	}

	public List<Topico> getTopicosMaisAvaliadosPostivo() {
		return topicosMaisAvaliadosPostivo;
	}

	public void setTopicosMaisAvaliadosPostivo(List<Topico> topicosMaisAvaliadosPostivo) {
		this.topicosMaisAvaliadosPostivo = topicosMaisAvaliadosPostivo;
	}

	public List<Topico> getTopicosMaisAvaliadosNegativo() {
		return topicosMaisAvaliadosNegativo;
	}

	public void setTopicosMaisAvaliadosNegativo(List<Topico> topicosMaisAvaliadosNegativo) {
		this.topicosMaisAvaliadosNegativo = topicosMaisAvaliadosNegativo;
	}

}
