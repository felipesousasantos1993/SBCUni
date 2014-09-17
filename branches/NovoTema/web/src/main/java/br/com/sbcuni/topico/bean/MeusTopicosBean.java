package br.com.sbcuni.topico.bean;

import java.io.Serializable;
import java.util.ArrayList;
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

@ViewScoped
@ManagedBean
public class MeusTopicosBean extends GenericBean implements Serializable {

	private static final long serialVersionUID = -8940436013758323941L;

	public MeusTopicosBean() {
		super();
	}
	
	private List<Topico> meusTopicos = new ArrayList<Topico>();
	
	@EJB
	private TopicoServiceBean topicoServiceBean;
	@EJB
	private AvaliacaoServiceBean avaliacaoServiceBean;
	
	private Topico topicoExcluir;
	
	@PostConstruct
	public void init() {
		meusTopicos = topicoServiceBean.buscarTopicoPorUsuario(UsuarioSessionBean.getInstance().getUsuarioSessao().getIdUsuario());
		for (Topico topico : meusTopicos) {
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
	
	
	public void topicoExcluir(Topico topico) {
		setTopicoExcluir(topico);
	}

	public List<Topico> getMeusTopicos() {
		return meusTopicos;
	}

	public void setMeusTopicos(List<Topico> meusTopicos) {
		this.meusTopicos = meusTopicos;
	}

	public Topico getTopicoExcluir() {
		return topicoExcluir;
	}

	public void setTopicoExcluir(Topico topicoExcluir) {
		this.topicoExcluir = topicoExcluir;
	}
}
