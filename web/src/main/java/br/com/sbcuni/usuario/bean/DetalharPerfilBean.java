package br.com.sbcuni.usuario.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class DetalharPerfilBean extends GenericBean {

	private static final long serialVersionUID = 7019958960117957350L;

	public DetalharPerfilBean() {
		super();
	}
	
	private Usuario usuario;

	@PostConstruct
	public void init() {
		usuario = (Usuario) WebResources.getFlash().get(WebResources.USUARIO);
		
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
