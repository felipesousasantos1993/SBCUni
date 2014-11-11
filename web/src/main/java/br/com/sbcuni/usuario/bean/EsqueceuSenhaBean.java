package br.com.sbcuni.usuario.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.mail.EmailException;

import br.com.sbcuni.bean.GenericBean;
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

	private String consulta;
	private String senha;
	
	private Usuario usuario;
	private Boolean novaSenha;

	public void consultarUsuario() {
		usuario = usuarioServiceBean.consultarPorMatriculaCpfEmail(consulta, consulta, consulta);
		if (!Util.isNull(usuario)) {
			novaSenha = Boolean.TRUE;
			try {
				Util.enviarEmailMandrill(usuario);
				exibirMsgSucesso("Senha enviada para seu e-mail");
			} catch (SbcuniException e) {
				exibirMsgErro("Erro ao enviar senha por e-mail");
			} catch (EmailException e) {
				exibirMsgErro("Erro ao enviar senha por e-mail");
			}
		} else {
			exibirMsgErro("Dados incorretos");
		}
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


	public String getConsulta() {
		return consulta;
	}


	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

}
