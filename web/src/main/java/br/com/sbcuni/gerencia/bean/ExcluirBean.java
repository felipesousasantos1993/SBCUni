package br.com.sbcuni.gerencia.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.usuario.service.UsuarioServiceBean;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class ExcluirBean extends GenericBean {

	private static final long serialVersionUID = 3879414622560543769L;

	public ExcluirBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		usuario = (Usuario) WebResources.getFlash().get(WebResources.USUARIO);
	}
	
	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	
	private Usuario usuario;
	
	public String excluirUsuario() {
		try {
			usuarioServiceBean.excluirUsuario(usuario);
			exibirMsgInfo("Usuário excluído com sucesso");
			return Tela.CONSULTAR;
		} catch (SbcuniException e) {
			exibirMsgErro(getMensagem("display.erro.excluir.usuario", WebResources.MENSAGEM));
			return null;
		}
	}
	
	public String telaDetalharUsuario() {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.DETALHAR_USUARIO;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
