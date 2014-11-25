package br.com.sbcuni.relatorios.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.relatorio.service.RelatorioServiceBean;
import br.com.sbcuni.relatorios.ConstantesRelatorio;
import br.com.sbcuni.relatorios.GeraRelatorioBean;

@ViewScoped
@ManagedBean
public class GruposComMaisTopicosBean extends GenericBean {

	private static final long serialVersionUID = -3760404623874839434L;

	@EJB
	private RelatorioServiceBean relatorioServiceBean;

	private List<GrupoEstudo> grupos;

	@PostConstruct
	public void init() {
		grupos = relatorioServiceBean.buscarGruposComMaisTopicos();
	}

	public void gerarPDF() {
		FacesContext context = FacesContext.getCurrentInstance();
		String reportUrlReal = ConstantesRelatorio.GRUPOS_COM_MAIS_TOPICOS;
		Map<String, Object> params = new HashMap<String, Object>();
		params = GenericBean.adicionaImagemCabecalho(params, context);

		params.put("titulo", "Grupos de Estudo com Mais Tópicos");

		try {
			GeraRelatorioBean.gerarPDF(grupos, params, reportUrlReal);
		} catch (Exception e) {
			exibirMsgErro("Erro ao Gerar Arquivo PDF!");
		}
	}

	public List<GrupoEstudo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoEstudo> grupos) {
		this.grupos = grupos;
	}

}
