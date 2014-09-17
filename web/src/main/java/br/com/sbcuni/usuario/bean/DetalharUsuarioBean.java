package br.com.sbcuni.usuario.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class DetalharUsuarioBean extends GenericBean {

	private static final long serialVersionUID = 2235249939755376453L;
	
	private Usuario usuario;
	 
	public DetalharUsuarioBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		usuario = (Usuario) WebResources.getFlash().get(WebResources.USUARIO);
	}
	
	public String telaAlterar() {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.ALTERAR_USUARIO;
	}
	
	public String telaConsultar() {
		WebResources.getFlash().put(WebResources.PERFIL, usuario.getPerfil());
		return Tela.CONSULTAR_USUARIO;
	}
	
	public String telaExcluir() {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.EXCLUIR_USUARIO;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	

}
