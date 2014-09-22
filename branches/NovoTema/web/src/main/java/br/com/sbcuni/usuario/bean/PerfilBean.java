package br.com.sbcuni.usuario.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.grupoEstudo.service.GrupoEstudoSerivceBean;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.WebResources;

@ViewScoped
@ManagedBean
public class PerfilBean extends GenericBean {

	private static final long serialVersionUID = 6740214399442770330L;

	public PerfilBean() {
		super();
	}
	@EJB
	private GrupoEstudoSerivceBean grupoEstudoSerivceBean;
	
	private List<GrupoEstudo> grupoEstudos;

	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		usuario = (Usuario) WebResources.getFlash().get(WebResources.USUARIO);
		grupoEstudos = grupoEstudoSerivceBean.consultarGruposUsuario(usuario);
	}
	
	public String telaMudarSenha() {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.MUDAR_SENHA_PATH;
	}
	

	public List<GrupoEstudo> getGrupoEstudos() {
		return grupoEstudos;
	}

	public void setGrupoEstudos(List<GrupoEstudo> grupoEstudos) {
		this.grupoEstudos = grupoEstudos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
