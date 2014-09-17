package br.com.sbcuni.gerencia.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.WebResources;

@ViewScoped
@ManagedBean
public class ListaBean extends GenericBean {

	private static final long serialVersionUID = 2998920969476481788L;

	public ListaBean() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){ 
		usuarios = (List<Usuario>) WebResources.getFlash().get(WebResources.LISTA_USUARIOS);
	}
	
	public String detalharUsuario(Usuario usuario) {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.DETALHAR;
	}
	public String voltarConsulta() {
		return Tela.CONSULTAR;
	}
	
	private List<Usuario> usuarios;

	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}	

}
