package br.com.sbcuni.categoria.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.categoria.entity.Categoria;
import br.com.sbcuni.categoria.service.CategoriaServiceBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.topico.service.TopicoServiceBean;
import br.com.sbcuni.util.Util;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class ManterCategoriasBean extends GenericBean {

	private static final long serialVersionUID = -6771030777454818051L;

	public ManterCategoriasBean() {
		super();
	}
	
	@EJB
	private CategoriaServiceBean categoriaServiceBean;
	@EJB
	private TopicoServiceBean topicoServiceBean;
	
	private List<Categoria> categorias;
	private Categoria categoria = new Categoria();
	private Categoria categoriaExcluir;
	
	@PostConstruct
	public void init() {
		carregarCategorias();
	}
	
	public String incluir() {
		Categoria catAux = categoriaServiceBean.buscarCategoriaPorDescricao(categoria.getDeCategoria());
		if (!Util.isNull(catAux)) {
			if (catAux.getDeCategoria().equalsIgnoreCase(categoria.getDeCategoria())) {
				exibirMsgErro(getMensagem("display.categoria.ja.existente", WebResources.MENSAGEM));
				return null;
			}
		}
		try {
			categoriaServiceBean.incluirCategoria(categoria);
			exibirMsgSucesso(getMensagem("display.categoria.incluida.sucesso", WebResources.MENSAGEM));
			carregarCategorias();
			return Tela.MANTER_CATEGORIA;
		} catch (SbcuniException e) {
			exibirMsgErro(getMensagem("display.erro.incluir.categoria", WebResources.MENSAGEM));
			return null;
		}
	}
	
	public String excluir() {
		try {
			categoriaServiceBean.excluirCategoria(categoriaExcluir);
			carregarCategorias();
			exibirMsgSucesso(getMensagem("display.categoria.excluida.sucesso", WebResources.MENSAGEM));
			return Tela.MANTER_CATEGORIA;
		} catch (Exception e) {
			exibirMsgErro(getMensagem("display.erro.excluir.caegoria", WebResources.MENSAGEM));
			return null;
		}
	}
	
	public void categoriaExcluir(Categoria categoria) {
		categoriaExcluir = categoria;
	}
	
	public void carregarCategorias() {
		categorias = topicoServiceBean.listarTodasCategorias();
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
