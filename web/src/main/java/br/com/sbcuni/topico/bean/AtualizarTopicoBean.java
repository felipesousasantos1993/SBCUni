package br.com.sbcuni.topico.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.categoria.entity.Categoria;
import br.com.sbcuni.categoria.service.CategoriaServiceBean;
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
		categorias = topicoServiceBean.listarTodasCategorias();
		for (Categoria tc : topico.getCategorias()) {
			categoriasSelecionadas.add(tc.getIdCategoria().toString());
		}
	}

	private Topico topico;
	
	private List<String> categoriasSelecionadas = new ArrayList<String>();
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	@EJB
	private TopicoServiceBean topicoServiceBean;
	@EJB
	private CategoriaServiceBean categoriaServiceBean;

	public String alterarTopico() {
		try {
			List<Categoria> categorias = new ArrayList<Categoria>();
			for (String idCategoria : categoriasSelecionadas) {
				categorias.add(categoriaServiceBean.buscarCategoriaPorId(Long.valueOf(idCategoria)));
			}
			topico.setCategorias(categorias);
			topico.setDtUltimaAtualizacao(new Date());
			topicoServiceBean.alterarTopico(topico);
			exibirMsgInfo(getMensagem("display.topico.alterado.sucesso", WebResources.MENSAGEM));
			return Tela.MEUS_TOPICOS;
		} catch (Exception e) {
			exibirMsgErro(getMensagem("display.erro.atualizar.topico", WebResources.MENSAGEM));
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

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<String> getCategoriasSelecionadas() {
		return categoriasSelecionadas;
	}

	public void setCategoriasSelecionadas(List<String> categoriasSelecionadas) {
		this.categoriasSelecionadas = categoriasSelecionadas;
	}


}
