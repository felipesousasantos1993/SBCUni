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
import br.com.sbcuni.constantes.Constantes;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.topico.service.TopicoServiceBean;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;
import br.com.sbcuni.util.Util;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class NovoTopicoBean extends GenericBean {

	private static final long serialVersionUID = -1908193349329912379L;

	@PostConstruct
	public void init() {
		grupoEstudo = (GrupoEstudo) WebResources.getFlash().get(WebResources.GRUPO_ESTUDO);
		categorias = topicoServiceBean.listarTodasCategorias();
	}

	@EJB
	private TopicoServiceBean topicoServiceBean;
	@EJB
	private CategoriaServiceBean categoriaServiceBean;

	private Topico novoTopico = new Topico();
	private List<Categoria> categorias;
	private GrupoEstudo grupoEstudo;
	
	private List<String> categoriasSelecionadas = new ArrayList<String>();
	
	/**
	 * Método : populaTopico Descrição:
	 */
	public void populaTopico() {
		novoTopico.setDtCriacao(new Date());
		novoTopico.setDtUltimaAtualizacao(new Date());
		novoTopico.setUsuario(UsuarioSessionBean.getInstance().getUsuarioSessao());
		novoTopico.setNuVisualizacoes(0);
		List<Categoria> categorias = new ArrayList<Categoria>();
		for (String idCategoria : categoriasSelecionadas) {
			categorias.add(categoriaServiceBean.buscarCategoriaPorId(Long.valueOf(idCategoria)));
		}
		novoTopico.setCategorias(categorias);
	}

	/**
	 * Método : postar Descrição:
	 */
	public String postarTopico() {
		populaTopico();
		try {
			if (!Util.isNull(grupoEstudo)) {
				novoTopico.setGrupoEstudo(grupoEstudo);
			}
			topicoServiceBean.postarTopico(novoTopico);
			exibirMsgSucesso(getMensagem("display.topico.criado.sucesso", Constantes.MENSAGEM));
			if (!Util.isNull(grupoEstudo)) {
				grupoEstudo.getTopicosGrupo().add(novoTopico);
				WebResources.getFlash().put(WebResources.GRUPO_ESTUDO, grupoEstudo);
				return Tela.DETALHE_GRUPO_ESTUDO_PATH;
			} else {
				return Tela.MEUS_TOPICOS;
			}
		} catch (Exception e) {
			exibirMsgAviso("Erro " + e.getMessage());
			return null;
		}

	}
	
	public String telaDetalharGrupo() {
		WebResources.getFlash().put(WebResources.GRUPO_ESTUDO, grupoEstudo);
		return Tela.DETALHE_GRUPO_ESTUDO;
	}
	
	public Topico getNovoTopico() {
		return novoTopico;
	}

	public void setNovoTopico(Topico novoTopico) {
		this.novoTopico = novoTopico;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}


	public GrupoEstudo getGrupoEstudo() {
		return grupoEstudo;
	}

	public void setGrupoEstudo(GrupoEstudo grupoEstudo) {
		this.grupoEstudo = grupoEstudo;
	}

	public List<String> getCategoriasSelecionadas() {
		return categoriasSelecionadas;
	}

	public void setCategoriasSelecionadas(List<String> categoriasSelecionadas) {
		this.categoriasSelecionadas = categoriasSelecionadas;
	}

}
