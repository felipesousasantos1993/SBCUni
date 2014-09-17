package br.com.sbcuni.grupoEstudo.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.avaliacao.service.AvaliacaoServiceBean;
import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class DetalheGrupoEstudoBean extends GenericBean {

	private static final long serialVersionUID = 1L;

	public DetalheGrupoEstudoBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		grupoEstudo = (GrupoEstudo) WebResources.getFlash().get(WebResources.GRUPO_ESTUDO);
		for (Topico t : grupoEstudo.getTopicosGrupo()) {
			avaliacaoServiceBean.definirAvaliacaoTopico(t);
		}
	}
	@EJB
	private AvaliacaoServiceBean avaliacaoServiceBean;
	
	private GrupoEstudo grupoEstudo;
	
	public String telaNovoTopico() {
		WebResources.getFlash().put(WebResources.GRUPO_ESTUDO, grupoEstudo);
		return Tela.NOVO_TOPICO_GRUPO_ESTUDO;
	}

	public GrupoEstudo getGrupoEstudo() {
		return grupoEstudo;
	}

	public void setGrupoEstudo(GrupoEstudo grupoEstudo) {
		this.grupoEstudo = grupoEstudo;
	}
	
}
