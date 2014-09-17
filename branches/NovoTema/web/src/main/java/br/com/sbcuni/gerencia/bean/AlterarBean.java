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
import br.com.sbcuni.util.Util;
import br.com.sbcuni.util.WebResources;

@ViewScoped
@ManagedBean
public class AlterarBean extends GenericBean {

	private static final long serialVersionUID = -8031320886105914458L;

	public AlterarBean() {
		super();
	}
	
	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	
	private Usuario usuario;
	
	@PostConstruct
	public void init() {
		usuario = (Usuario) WebResources.getFlash().get(WebResources.USUARIO);
	}
	
	public String alterarUsuario() {
		try {
			usuario.setCpf(Util.retiraMascara(usuario.getCpf()));
			usuarioServiceBean.alterarUsuario(usuario);
			WebResources.getFlash().put(WebResources.USUARIO, usuario);
			exibirMsgInfo("Usuário alterado com sucesso");
			return Tela.DETALHAR;
		} catch (SbcuniException e) {
			exibirMsgAviso(e.getMessage());
		}
		return null;
	}
	
	public void gerarMatricula() {
		usuario.setMatricula(Util.gerarMatricula(usuario.getPerfil()));
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
