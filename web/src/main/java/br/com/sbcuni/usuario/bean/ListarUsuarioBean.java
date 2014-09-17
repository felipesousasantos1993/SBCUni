package br.com.sbcuni.usuario.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class ListarUsuarioBean extends GenericBean {

	private static final long serialVersionUID = 8636738891539864223L;

	public ListarUsuarioBean() {
		super();
	}
	
	private List<Usuario> usuarios;
	
	private Integer perfil;
	

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		usuarios = (List<Usuario>) WebResources.getFlash().get(WebResources.LISTA_USUARIOS);
		perfil = (Integer) WebResources.getFlash().get(WebResources.PERFIL);
	}
	
	public String detalharUsuario(Usuario usuario) {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.DETALHAR_USUARIO;
	}

	public String voltarTelaConsulta() {
		WebResources.getFlash().put(WebResources.PERFIL, perfil);
		return Tela.CONSULTAR_USUARIO;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public Integer getPerfil() {
		return perfil;
	}
	
	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}
}
