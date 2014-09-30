package br.com.sbcuni.usuario.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Constantes;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.usuario.service.UsuarioServiceBean;
import br.com.sbcuni.util.Util;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class AlterarUsuarioBean extends GenericBean {

	private static final long serialVersionUID = -2729332936086625300L;

	public AlterarUsuarioBean() {
		super();
	}
	
	private Usuario usuario;
	private Boolean alterarSenha = Boolean.FALSE;
	private Boolean inativo;
	

	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	
	@PostConstruct
	public void init() {
		usuario = (Usuario) WebResources.getFlash().get(WebResources.USUARIO);
	}

	public String alterarUsuario() {
		try {
			if (inativo) {
				usuario.setStatus(Boolean.FALSE);
			} else {
				usuario.setStatus(Boolean.TRUE);
			}
			usuario.setCpf(Util.retiraMascara(usuario.getCpf()));
			usuarioServiceBean.alterarUsuario(usuario);
			if (usuario.getPerfil().equals(Constantes.PERFIL_ALUNO)) {
				exibirMsgSucesso(getMensagem("display.aluno.alterado.sucesso", WebResources.MENSAGEM));
			} else if (usuario.getPerfil().equals(Constantes.PERFIL_PROFESSOR)) {
				exibirMsgSucesso(getMensagem("display.professor.alterado.sucesso", WebResources.MENSAGEM));
			} else if (usuario.getPerfil().equals(Constantes.PERFIL_COODERNADOR)) {
				exibirMsgSucesso(getMensagem("display.coordenador.alterado.sucesso", WebResources.MENSAGEM));
			}
			WebResources.getFlash().put(WebResources.USUARIO, usuario);
			return Tela.DETALHAR_USUARIO;
		} catch (SbcuniException e) {
			exibirMsgErro(getMensagem(e.getMessage(), WebResources.MENSAGEM));
			return null;
		}
	}
	
	public String voltarDetalheUsuario() {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.DETALHAR_USUARIO;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getAlterarSenha() {
		return alterarSenha;
	}

	public void setAlterarSenha(Boolean alterarSenha) {
		this.alterarSenha = alterarSenha;
	}

	public Boolean getInativo() {
		return inativo;
	}

	public void setInativo(Boolean inativo) {
		this.inativo = inativo;
	}
	
}
