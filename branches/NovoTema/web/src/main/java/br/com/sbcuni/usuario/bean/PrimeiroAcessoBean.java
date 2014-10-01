package br.com.sbcuni.usuario.bean;

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
public class PrimeiroAcessoBean extends GenericBean {

	private static final long serialVersionUID = -7799276250466219273L;

	public PrimeiroAcessoBean() {
		super();
	}

	private String matricula;
	private String senha;
	private Usuario usuario;
	private Boolean inputSenha = Boolean.FALSE;
	private Boolean btnCriar = Boolean.FALSE;

	@EJB
	private UsuarioServiceBean usuarioServiceBean;

	public void verificarMatricula() {
		usuario = usuarioServiceBean.consultarPorMatricula(matricula, 0);
		if (Util.isNull(usuario)) {
			exibirMsgInfo(getMensagem("display.matricula.nao.encontrada", WebResources.MENSAGEM));
		} else {
			if (Util.isBlankOrNull(usuario.getSenha())) {
				inputSenha = Boolean.TRUE;
			} else {
				exibirMsgInfo(getMensagem("display.usuario.ja.possui.senha", WebResources.MENSAGEM));
			}
		}
	}
	
	public void validaSenha() {
		if (senha.length() >= 6) {
			btnCriar = Boolean.TRUE;
		}
	}
	
	public String criarSenha() {
		usuario.setSenha(senha);
		try {
			usuarioServiceBean.alterarUsuario(usuario);
			exibirMsgSucesso(getMensagem("display.senha.criada.sucesso", WebResources.MENSAGEM));
			return Tela.LOGIN;
		} catch (SbcuniException e) {
			exibirMsgErro(e.getMessage());
			return null;
		}
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getInputSenha() {
		return inputSenha;
	}

	public void setInputSenha(Boolean inputSenha) {
		this.inputSenha = inputSenha;
	}

	public Boolean getBtnCriar() {
		return btnCriar;
	}

	public void setBtnCriar(Boolean btnCriar) {
		this.btnCriar = btnCriar;
	}

}
