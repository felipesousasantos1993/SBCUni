package br.com.sbcuni.topico.bean;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.avaliacao.service.AvaliacaoServiceBean;
import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.categoria.service.CategoriaServiceBean;
import br.com.sbcuni.comentario.entity.Comentario;
import br.com.sbcuni.comentario.service.ComentarioServiceBean;
import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.grupoEstudo.service.GrupoEstudoSerivceBean;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;
import br.com.sbcuni.util.Util;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class VisualizarTopicoBean extends GenericBean {

	private static final long serialVersionUID = -732011456260770242L;

	private Topico topico;
	
	@EJB
	private ComentarioServiceBean comentarioServiceBean;
	@EJB
	private AvaliacaoServiceBean avaliacaoServiceBean;
	@EJB
	private GrupoEstudoSerivceBean grupoEstudoSerivceBean;
	@EJB
	private CategoriaServiceBean categoriaServiceBean;
	
	@PostConstruct
	public void init() {
		topico = (Topico) WebResources.getFlash().get(WebResources.TOPICO);
		topico.setComentarios(comentarioServiceBean.consultarComentariosTopico(topico));
		topico.setGrupoEstudo(grupoEstudoSerivceBean.consultarGrupoTopico(topico));
		topico.setCategorias(categoriaServiceBean.buscarCategoriaTopico(topico));
		avaliacaoServiceBean.definirAvaliacaoTopico(topico);
		topico.setAvaliacaoUsuario(avaliacaoServiceBean.verificarAvaliacaoUsuarioTopico(UsuarioSessionBean.getInstance().getUsuarioSessao(), topico));
		for (Comentario comentario : topico.getComentarios()) {
			avaliacaoServiceBean.definirAvaliacaoComentario(comentario);
			comentario.setAvaliacaoUsuario(avaliacaoServiceBean.verificarAvaliacaoUsuarioComentario(UsuarioSessionBean.getInstance().getUsuarioSessao(), comentario));
		}

	}

	private Boolean textAreaComentario = Boolean.FALSE;
	private Boolean msgExcluirComentario = Boolean.FALSE;
	
	private Comentario comentario = new Comentario();
	
	private int idComentario;
	private Integer idEditar = null;
	
	public void comentar() {
		if (Util.isNull(comentario.getIdComentario())) {
			comentario.setDtCriacao(new Date());
		} else {
			comentario.setDtUltimaAtualizacao(new Date());
		}
		comentario.setUsuario(UsuarioSessionBean.getInstance().getUsuarioSessao());
		comentario.setTopico(topico);
		try {
			comentarioServiceBean.comentar(comentario);
			topico.setComentarios(comentarioServiceBean.consultarComentariosTopico(topico));
			textAreaComentario = Boolean.FALSE;
			comentario = new Comentario();
			desabilitarCampoComentario();
			for (Comentario comentario : topico.getComentarios()) {
				avaliacaoServiceBean.definirAvaliacaoComentario(comentario);
				comentario.setAvaliacaoUsuario(avaliacaoServiceBean.verificarAvaliacaoUsuarioComentario(UsuarioSessionBean.getInstance().getUsuarioSessao(), comentario));
			}
		} catch (SbcuniException e) {
			exibirMsgErro(e.getMessage());
		}
	}
	
	public void excluirComentario(Comentario c) {
		try {
			comentarioServiceBean.excluirComentario(c);
			topico.setComentarios(comentarioServiceBean.consultarComentariosTopico(topico));
			for (Comentario comentario : topico.getComentarios()) {
				avaliacaoServiceBean.definirAvaliacaoComentario(comentario);
				comentario.setAvaliacaoUsuario(avaliacaoServiceBean.verificarAvaliacaoUsuarioComentario(UsuarioSessionBean.getInstance().getUsuarioSessao(), comentario));
			}
			idComentario = -1;
		} catch (Exception e) {
			exibirMsgErro(e.getMessage());
		}
	}
	
	public void habilitarCampoComentario() {
		setTextAreaComentario(Boolean.TRUE);
	}
	
	public void desabilitarCampoComentario() {
		setTextAreaComentario(Boolean.FALSE);
		setIdEditar(null);
	}
	
	public void exibirMsgExcluir(Integer id) {
		msgExcluirComentario = Boolean.TRUE;
		idComentario = id;
	}
	
	public void fecharMsgExcluir() {
		msgExcluirComentario = Boolean.FALSE;
	}
	
	public void editarComentario(Comentario c, Integer id) {
		textAreaComentario = Boolean.TRUE;
		comentario = c;
		idEditar = id;
	}
	
	
	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public Boolean getTextAreaComentario() {
		return textAreaComentario;
	}

	public void setTextAreaComentario(Boolean textAreaComentario) {
		this.textAreaComentario = textAreaComentario;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public Boolean getMsgExcluirComentario() {
		return msgExcluirComentario;
	}

	public void setMsgExcluirComentario(Boolean msgExcluirComentario) {
		this.msgExcluirComentario = msgExcluirComentario;
	}

	public int getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}

	public Integer getIdEditar() {
		return idEditar;
	}

	public void setIdEditar(Integer idEditar) {
		this.idEditar = idEditar;
	}
}
