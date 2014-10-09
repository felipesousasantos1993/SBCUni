package br.com.sbcuni.categoria.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.avaliacao.service.AvaliacaoServiceBean;
import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.comentario.service.ComentarioServiceBean;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class TopicosCategoriaBean extends GenericBean {

	private static final long serialVersionUID = 4270729512919539079L;

	public TopicosCategoriaBean() {
		super();
	}

	private List<Topico> topicos = new ArrayList<Topico>();

	@EJB
	private AvaliacaoServiceBean avaliacaoServiceBean;
	@EJB
	private ComentarioServiceBean comentarioServiceBean;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		topicos = (List<Topico>) WebResources.getFlash().get(WebResources.LISTA_TOPICOS);
		for (Topico topico : topicos) {
			topico.setComentarios(comentarioServiceBean.consultarComentariosTopico(topico));
			avaliacaoServiceBean.definirAvaliacaoTopico(topico);
			topico.setAvaliacaoUsuario(avaliacaoServiceBean.verificarAvaliacaoUsuarioTopico(UsuarioSessionBean.getInstance().getUsuarioSessao(), topico));
		}
	}

	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

}
