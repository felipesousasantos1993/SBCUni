package br.com.sbcuni.usuario.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.usuario.service.UsuarioServiceBean;
import br.com.sbcuni.util.WebResources;

@ViewScoped
@ManagedBean
public class MudarSenhaBean extends GenericBean {

	private static final long serialVersionUID = 4950400413188035985L;

	public MudarSenhaBean() {
		super();
	}
	
	private Usuario usuario;
	private String senhaAntiga;
	private String senhaNova;
	
	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	
	@PostConstruct
	public void init(){
		usuario = (Usuario) WebResources.getFlash().get(WebResources.USUARIO);
	}
	
	public String alterarSenha() {
		if (!senhaAntiga.equals(usuario.getSenha())) {
			exibirMsgAviso(GenericBean.getMensagem("display.senha.antiga.nao.confere", WebResources.MENSAGEM));
			return null;
		} else {
			try {
				usuario.setSenha(senhaNova);
				usuarioServiceBean.alterarUsuario(usuario);
			} catch (SbcuniException e) {
				exibirMsgErro("Erro ao alterar senha");
			}
			UsuarioSessionBean.getInstance().destruirSessao();
			UsuarioSessionBean.getInstance().iniciarSessao(WebResources.USUARIO, usuario);
			WebResources.getFlash().put(WebResources.USUARIO, usuario);
			exibirMsgSucesso(GenericBean.getMensagem("display.senha.alterada.sucesso", WebResources.MENSAGEM));
			return Tela.PEFIL_PATH;
		}
	}
	
	public String voltarTelaPerfil() {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.PEFIL_PATH;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}
	
}
