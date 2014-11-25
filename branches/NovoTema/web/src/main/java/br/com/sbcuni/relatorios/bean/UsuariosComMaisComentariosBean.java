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
import br.com.sbcuni.usuario.entity.Usuario;

@ViewScoped
@ManagedBean
public class UsuariosComMaisComentariosBean extends GenericBean {

	private static final long serialVersionUID = -3760404623874839434L;

	@EJB
	private RelatorioServiceBean relatorioServiceBean;

	private List<Usuario> usuarios;

	@PostConstruct
	public void init() {
		usuarios = relatorioServiceBean.buscarUsuariosComMaisComentarios();
	}

	public void gerarPDF() {
		FacesContext context = FacesContext.getCurrentInstance();
		String reportUrlReal = ConstantesRelatorio.USUARIOS_COM_MAIS_COMENTARIOS;
		Map<String, Object> params = new HashMap<String, Object>();
		params = GenericBean.adicionaImagemCabecalho(params, context);
		
		params.put("titulo", "Usuários que Mais Comentam");

		try {
			GeraRelatorioBean.gerarPDF(usuarios, params, reportUrlReal);
		} catch (Exception e) {
			exibirMsgErro("Erro ao Gerar Arquivo PDF!");
		}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
