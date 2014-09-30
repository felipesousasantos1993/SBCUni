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

@ManagedBean
@ViewScoped
public class CadastrarBean extends GenericBean {

	private static final long serialVersionUID = -8231190679759361158L;

	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	
	private Usuario usuario = new Usuario();
	
	private Boolean inativo;
	
	
	
	@PostConstruct
	public void init() {
		usuario.setPerfil(0);
	}
	
	public String cadastrarUsuario() {
		try {
			if (inativo) {
				usuario.setStatus(Boolean.FALSE);
			} else {
				usuario.setStatus(Boolean.TRUE);
			}
			usuario.setCpf(Util.retiraMascara(usuario.getCpf()));
			usuarioServiceBean.cadastrarUsuario(usuario);
			WebResources.getFlash().put(WebResources.USUARIO, usuario);
			exibirMsgInfo(getMensagem("display.usuario.cadastrado.sucesso", WebResources.MENSAGEM));
			return Tela.DETALHAR;
		} catch (SbcuniException e) {
			exibirMsgAviso(getMensagem(e.getMessage(), WebResources.MENSAGEM));
			return null;
		} catch (Exception e) {
			exibirMsgErro(getMensagem(WebResources.ERRO_INESPERADO, WebResources.MENSAGEM));
			return null;
		}
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

	public Boolean getInativo() {
		return inativo;
	}

	public void setInativo(Boolean inativo) {
		this.inativo = inativo;
	}
}
