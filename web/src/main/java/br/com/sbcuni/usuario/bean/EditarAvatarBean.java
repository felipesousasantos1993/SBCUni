package br.com.sbcuni.usuario.bean;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.usuario.service.UsuarioServiceBean;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class EditarAvatarBean extends GenericBean {

	private static final long serialVersionUID = 4602050429391097163L;

	public EditarAvatarBean() {
		super();
	}
	
	private Usuario usuario;
	private File[] avatares;
	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	
	@PostConstruct
	public void init() {
		usuario = UsuarioSessionBean.getInstance().getUsuarioSessao();
		File dir = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/img/avatares"));
		avatares = dir.listFiles();
	}
	
	public String editarAvatar() {
		try {
			usuarioServiceBean.alterarUsuario(usuario);
			WebResources.getFlash().put(WebResources.USUARIO, usuario);
			exibirMsgSucesso(getMensagem("display.avatar.atualizado.sucesso", WebResources.MENSAGEM));
			return Tela.PEFIL_PATH;
		} catch (SbcuniException e) {
			exibirMsgAviso(getMensagem("display.erro.editar.avatar", WebResources.MENSAGEM));
			return null;
		}
	}
	
	public void definirAvatar(String avatar) {
		usuario.setAvatar(avatar);
	}
	
	public String voltarTelaPerfil() {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.PEFIL_PATH;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public File[] getAvatares() {
		return avatares;
	}

	public void setAvatares(File[] avatares) {
		this.avatares = avatares;
	}
	
}
