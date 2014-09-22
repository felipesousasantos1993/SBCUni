package br.com.sbcuni.menu.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class MenuBean extends GenericBean {

	private static final long serialVersionUID = -1773264209189783322L;

	public MenuBean() {
		super();
	}
	
	public String telaCadastrarUsuario(Integer perfil) {
		WebResources.getFlash().put(WebResources.PERFIL, perfil);
		return Tela.CADASTRAR_USUARIO_MENU;
	}
	
	public String telaConsultarUsuario(Integer perfil) {
		WebResources.getFlash().put(WebResources.PERFIL, perfil);
		return Tela.CONSULTAR_USUARIO_MENU;
	}
	
	public String telaPerfil(Usuario usuario) {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.PEFIL_PATH;
	}
	
}
