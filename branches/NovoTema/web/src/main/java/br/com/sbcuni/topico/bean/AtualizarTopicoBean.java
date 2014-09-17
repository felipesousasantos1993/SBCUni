package br.com.sbcuni.topico.bean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.categoria.entity.Categoria;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.topico.service.TopicoServiceBean;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class AtualizarTopicoBean extends GenericBean {

	private static final long serialVersionUID = -1287188921421812460L;

	@PostConstruct
	public void init() {
		topico = (Topico) WebResources.getFlash().get(WebResources.TOPICO);
		categoriasSelecionadas = topico.getCategorias();
	}

	private Topico topico;
	
	private List<Categoria> categoriasSelecionadas;
	
	@EJB
	private TopicoServiceBean topicoServiceBean;

	public String alterarTopico() {
		try {
			topico.setDtUltimaAtualizacao(new Date());
			topicoServiceBean.alterarTopico(topico);
			exibirMsgInfo(getMensagem("display.topico.alterado.sucesso", WebResources.MENSAGEM));
			return Tela.MEUS_TOPICOS;
		} catch (Exception e) {
			exibirMsgErro("Erro na Alteração do Tópico");
			return null;
		}
	}
	
	public String voltar() {
		return Tela.MEUS_TOPICOS;
	}

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	public List<Categoria> getCategoriasSelecionadas() {
		return categoriasSelecionadas;
	}

	public void setCategoriasSelecionadas(List<Categoria> categoriasSelecionadas) {
		this.categoriasSelecionadas = categoriasSelecionadas;
	}

}
