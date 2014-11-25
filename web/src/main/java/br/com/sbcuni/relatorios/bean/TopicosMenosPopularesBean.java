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
import br.com.sbcuni.relatorio.service.RelatorioServiceBean;
import br.com.sbcuni.relatorios.ConstantesRelatorio;
import br.com.sbcuni.relatorios.GeraRelatorioBean;
import br.com.sbcuni.topico.entity.Topico;

@ViewScoped
@ManagedBean
public class TopicosMenosPopularesBean extends GenericBean {

	private static final long serialVersionUID = -3760404623874839434L;

	@EJB
	private RelatorioServiceBean relatorioServiceBean;
	
	private List<Topico> topicos;

	@PostConstruct
	public void init() {
		topicos = relatorioServiceBean.buscarTopicosMaisMalAvaliados();
	}

	public void gerarPDF() {
		FacesContext context = FacesContext.getCurrentInstance();
		String reportUrlReal = ConstantesRelatorio.TOPICOS_MENOS_POPULARES;
		Map<String, Object> params = new HashMap<String, Object>();
		params = GenericBean.adicionaImagemCabecalho(params, context);

		
		params.put("titulo", "Tópicos menos populares");

		try {
			GeraRelatorioBean.gerarPDF(topicos, params, reportUrlReal);
		} catch (Exception e) {
			exibirMsgErro("Erro ao Gerar Arquivo PDF!");
		}
	}

	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}
}
