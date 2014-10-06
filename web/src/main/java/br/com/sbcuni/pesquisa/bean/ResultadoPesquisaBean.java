package br.com.sbcuni.pesquisa.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.mensagem.entity.Mensagem;
import br.com.sbcuni.topico.entity.Topico;
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

	private Integer nuResultados;
	private String consulta;
	
	@PostConstruct
	public void init() {
		topicos = (List<Topico>) WebResources.getFlash().get(WebResources.LISTA_TOPICOS);
		nuResultados = (Integer) WebResources.getFlash().get(WebResources.NU_RESULTADOS_PESQUISA);
		consulta = (String) WebResources.getFlash().get(WebResources.PESQUISA);
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

}
