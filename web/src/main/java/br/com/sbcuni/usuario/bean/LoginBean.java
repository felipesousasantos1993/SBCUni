package br.com.sbcuni.usuario.bean;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.usuario.service.UsuarioServiceBean;
import br.com.sbcuni.util.Util;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class LoginBean extends GenericBean {

	private static final long serialVersionUID = -2391442136287626467L;

	public LoginBean() {
		super();
	}

	@EJB
	private UsuarioServiceBean usuarioServiceBean;

	private String matricula;
	private String senha;

	public String logar() {
		try {
			Usuario usuario = usuarioServiceBean.consultarUsuarioLogin(matricula, senha);
			if (Util.isNull(usuario)) {
				exibirMsgInfo(getMensagem("display.usuario.nao.encontrado", WebResources.MENSAGEM));
				return null;
			} else {
				UsuarioSessionBean.getInstance().iniciarSessao(WebResources.USUARIO, usuario);
				usuario.setDtUltimoAcesso(new Date());
				usuarioServiceBean.alterarUsuario(usuario);
				return Tela.PAINEL_PRINCIPAL;
			}
		} catch (Exception e) {
			exibirMsgInfo(getMensagem("display.usuario.nao.encontrado", WebResources.MENSAGEM));
			return null;
		}
	}

	public String deslogar() {
		exibirMsgInfo("Usuário " + UsuarioSessionBean.getInstance().getUsuarioSessao().getNome() + " deslogado(a) com sucesso!");
		UsuarioSessionBean.getInstance().destruirSessao();
		return Tela.LOGIN;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
