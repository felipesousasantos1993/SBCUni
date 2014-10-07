package br.com.sbcuni.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.sbcuni.usuario.entity.Usuario;

public abstract class Sessao {

	private HttpSession session;

	protected HttpSession getSession() {
		return session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}

	protected void setSessao(String argumento, Usuario usuario) {
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute(argumento, usuario);
	}
	
	protected void destruir() {
		session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
	}

	protected boolean sessaoExiste(String argumento) {
		boolean result = (getSession().getAttribute(argumento) != null);
		return result;
	}

	public static Sessao getInstance() {
		return new Sessao() {
		};
	}

}
