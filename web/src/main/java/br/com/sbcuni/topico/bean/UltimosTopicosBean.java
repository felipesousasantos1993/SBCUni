package br.com.sbcuni.topico.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.avaliacao.service.AvaliacaoServiceBean;
import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.topico.service.TopicoServiceBean;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class UltimosTopicosBean extends GenericBean  {

	private static final long serialVersionUID = -8789465342485705143L;

	public List<Topico> topicos;

	@EJB
	private TopicoServiceBean topicoServiceBean;
	@EJB
	private AvaliacaoServiceBean avaliacaoServiceBean;
	
	private Topico topicoExcluir;
	
	@PostConstruct
	public void init() {
		topicos = topicoServiceBean.buscarTodosTopicos();
		for (Topico topico : topicos) {
			avaliacaoServiceBean.definirAvaliacaoTopico(topico);
			topico.setAvaliacaoUsuario(avaliacaoServiceBean.verificarAvaliacaoUsuarioTopico(UsuarioSessionBean.getInstance().getUsuarioSessao(), topico));
		}
	}
	
	public String excluir() {
		try {
			topicoServiceBean.excluirTopico(topicoExcluir);
			exibirMsgAviso(getMensagem("display.topico.excluido.sucesso", WebResources.MENSAGEM));
			return Tela.MEUS_TOPICOS;
		} catch (Exception e) {
			exibirMsgErro(getMensagem("display.erro.ao.excluir.topico", WebResources.MENSAGEM));
			return null;
		}
	}

	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}
	
	public void topicoExcluir(Topico topico) {
		setTopicoExcluir(topico);
	}

	public Topico getTopicoExcluir() {
		return topicoExcluir;
	}

	public void setTopicoExcluir(Topico topicoExcluir) {
		this.topicoExcluir = topicoExcluir;
	}
	
}
