package br.com.sbcuni.usuario.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.Sessao;

@SessionScoped
@ManagedBean
public class UsuarioSessionBean extends Sessao implements Serializable {

	private static final long serialVersionUID = -6156757611744247993L;

	public UsuarioSessionBean() {
		super();
	}

	private String argumento;

	public void iniciarSessao(String arg, Usuario usuario) {
		this.argumento = arg.toLowerCase();
		if (!sessaoExiste(argumento)) {
			setSessao(argumento, usuario);
		}
	}

	public boolean isSessionNotNull(String argumento) {
		return (sessaoExiste(argumento.toLowerCase()));
	}

	public String destruirSessao() {
		destruir();
		return Tela.LOGIN;
	}
	
	public void removerSessao() {
		destruir();
	}

	public Usuario getUsuarioSessao() {
		this.argumento = Usuario.class.getSimpleName().toLowerCase();
		if (sessaoExiste(argumento)) {
			Usuario funcionario = (Usuario) getSession().getAttribute(argumento);
			return funcionario;
		} else {
			return null;
		}
	}

	public static UsuarioSessionBean getInstance() {
		return new UsuarioSessionBean();
	}

	public void logarUsuario(Usuario usuario) {
	}

}
