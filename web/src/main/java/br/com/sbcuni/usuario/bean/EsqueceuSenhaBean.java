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

@ManagedBean
@ViewScoped
public class EsqueceuSenhaBean extends GenericBean {

	private static final long serialVersionUID = 905746583491942725L;

	public EsqueceuSenhaBean() {
		super();
	}

	@EJB
	private UsuarioServiceBean usuarioServiceBean;

	private String matricula;
	private String cpf;
	private String senha;
	
	private Usuario usuario;
	private Boolean novaSenha;

	public void consultarUsuario() {
		usuario = usuarioServiceBean.consultarPorMatriculaCpf(cpf, matricula);
		if (!Util.isNull(usuario)) {
			novaSenha = Boolean.TRUE;
		} else {
			exibirMsgAviso("Dados incorretos");
		}
	}
	
	public String criarNovasenha() {
		usuario.setSenha(senha);
		try {
			usuarioServiceBean.alterarUsuario(usuario);
			exibirMsgSucesso("Senha criada com sucesso");
			return Tela.LOGIN;
		} catch (SbcuniException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public Boolean getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(Boolean novaSenha) {
		this.novaSenha = novaSenha;
	}

}
