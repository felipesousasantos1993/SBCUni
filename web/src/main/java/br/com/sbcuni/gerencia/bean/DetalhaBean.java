package br.com.sbcuni.gerencia.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class DetalhaBean extends GenericBean {

	private static final long serialVersionUID = 7215611832757871507L;

	public DetalhaBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		usuario = (Usuario) WebResources.getFlash().get(WebResources.USUARIO);
	}
	
	private Usuario usuario;

	public String telaAlterar() {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.ALTERAR;
	}
	
	public String telaConfirmaExcluir() {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.EXCLUIR;
	}
	public String telaConsultar() {
		return Tela.CONSULTAR;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
