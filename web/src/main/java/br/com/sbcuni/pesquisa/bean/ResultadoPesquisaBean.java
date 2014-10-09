package br.com.sbcuni.pesquisa.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.categoria.entity.Categoria;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.mensagem.entity.Mensagem;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class ResultadoPesquisaBean extends GenericBean {

	private static final long serialVersionUID = -4782704806788177813L;

	public ResultadoPesquisaBean() {
		super();
	}

	private List<Topico> topicos;
	private List<Mensagem> mensagens;
	private List<GrupoEstudo> grupoEstudos;
	private List<Usuario> usuarios;
	private List<Categoria> categorias;

	private Integer nuResultados;
	private Integer nuTopicos;
	private Integer nuUsuarios;
	private Integer nuMensagens;
	private Integer nuGrupos;
	private Integer nuCategorias;
	private String consulta;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		topicos = (List<Topico>) WebResources.getFlash().get(WebResources.LISTA_TOPICOS);
		usuarios = (List<Usuario>) WebResources.getFlash().get(WebResources.LISTA_USUARIOS);
		grupoEstudos = (List<GrupoEstudo>) WebResources.getFlash().get(WebResources.LISTA_GRUPOS_ESTUDO);
		mensagens = (List<Mensagem>) WebResources.getFlash().get(WebResources.LISTA_MENSAGENS);
		grupoEstudos = (List<GrupoEstudo>) WebResources.getFlash().get(WebResources.LISTA_GRUPOS_ESTUDO);
		mensagens = (List<Mensagem>) WebResources.getFlash().get(WebResources.LISTA_MENSAGENS);
		categorias = (List<Categoria>) WebResources.getFlash().get(WebResources.LISTA_CATEGORIAS);
		nuResultados = (Integer) WebResources.getFlash().get(WebResources.NU_RESULTADOS_PESQUISA);
		consulta = (String) WebResources.getFlash().get(WebResources.PESQUISA);
		for (Usuario u : usuarios) {
			u.setIndice(usuarios.indexOf(u) + 1);
		}
		try {
			nuTopicos = topicos.size();
			nuUsuarios = usuarios.size();
			nuGrupos = grupoEstudos.size();
			nuMensagens = mensagens.size();
			nuCategorias = categorias.size();
		} catch (Exception e) {
		}
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

	public List<GrupoEstudo> getGrupoEstudos() {
		return grupoEstudos;
	}

	public void setGrupoEstudos(List<GrupoEstudo> grupoEstudos) {
		this.grupoEstudos = grupoEstudos;
	}

	public Integer getNuResultados() {
		return nuResultados;
	}

	public void setNuResultados(Integer nuResultados) {
		this.nuResultados = nuResultados;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Integer getNuGrupos() {
		return nuGrupos;
	}

	public void setNuGrupos(Integer nuGrupos) {
		this.nuGrupos = nuGrupos;
	}

	public Integer getNuTopicos() {
		return nuTopicos;
	}

	public void setNuTopicos(Integer nuTopicos) {
		this.nuTopicos = nuTopicos;
	}

	public Integer getNuUsuarios() {
		return nuUsuarios;
	}

	public void setNuUsuarios(Integer nuUsuarios) {
		this.nuUsuarios = nuUsuarios;
	}

	public Integer getNuMensagens() {
		return nuMensagens;
	}

	public void setNuMensagens(Integer nuMensagens) {
		this.nuMensagens = nuMensagens;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Integer getNuCategorias() {
		return nuCategorias;
	}

	public void setNuCategorias(Integer nuCategorias) {
		this.nuCategorias = nuCategorias;
	}

}
