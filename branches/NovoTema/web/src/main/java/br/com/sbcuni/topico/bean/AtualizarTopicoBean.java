package br.com.sbcuni.topico.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

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
		for (Categoria cat : topicoServiceBean.listarTodasCategorias()) {
			categoriasTopico.add(new SelectItem(cat.getIdCategoria(), cat.getDeCategoria()));
		}
		for (Categoria c : topico.getCategorias()) {
			categoriasSelecionadas.add(new SelectItem(c.getIdCategoria(), c.getDeCategoria()));
		}
	}

	private Topico topico;
	
	private List<SelectItem> categoriasSelecionadas = new ArrayList<SelectItem>();
	private List<SelectItem> categoriasTopico = new ArrayList<SelectItem>();
	
	@EJB
	private TopicoServiceBean topicoServiceBean;
	@EJB
	private CategoriaServiceBean categoriaServiceBean;

	public String alterarTopico() {
		try {
			List<Categoria> categorias = new ArrayList<Categoria>();
		/*	for (String idCategoria : categoriasSelecionadas) {
				categorias.add(categoriaServiceBean.buscarCategoriaPorId(Long.valueOf(idCategoria)));
			}*/
			topico.setCategorias(categorias);
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

	public List<SelectItem> getCategoriasSelecionadas() {
		return categoriasSelecionadas;
	}

	public void setCategoriasSelecionadas(List<SelectItem> categoriasSelecionadas) {
		this.categoriasSelecionadas = categoriasSelecionadas;
	}

	public List<SelectItem> getCategoriasTopico() {
		return categoriasTopico;
	}

	public void setCategoriasTopico(List<SelectItem> categoriasTopico) {
		this.categoriasTopico = categoriasTopico;
	}


}
