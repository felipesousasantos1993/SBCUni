package br.com.sbcuni.aluno.bean;

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
public class CadastrarUsuarioBean extends GenericBean {

	private static final long serialVersionUID = -4918512669665565275L;

	public CadastrarUsuarioBean() {
		super();
	}
	
	private Usuario usuario = new Usuario();
	private Boolean inativo;
	
	@PostConstruct
	public void init() {
		usuario.setPerfil((Integer) WebResources.getFlash().get(WebResources.PERFIL));
	}
	
	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	
	public String cadastrarUsuario() {
		try {
			usuario.setMatricula(Util.gerarMatricula(usuario.getPerfil()));
			usuario.setAvatar(Constantes.FOTO_PADRAO);
			usuario.setCpf(Util.retiraMascara(usuario.getCpf()));
			usuarioServiceBean.cadastrarUsuario(usuario);
			if (usuario.getPerfil().equals(Constantes.PERFIL_ALUNO)) {
				exibirMsgSucesso(getMensagem("display.aluno.cadastrado.sucesso", WebResources.MENSAGEM));
			} else if (usuario.getPerfil().equals(Constantes.PERFIL_PROFESSOR)) {
				exibirMsgSucesso(getMensagem("display.professor.cadastrado.sucesso", WebResources.MENSAGEM));
			} else if (usuario.getPerfil().equals(Constantes.PERFIL_COODERNADOR)) {
				exibirMsgSucesso(getMensagem("display.coordenador.cadastrado.sucesso", WebResources.MENSAGEM));
			}
			WebResources.getFlash().put(WebResources.USUARIO, usuario);
			return Tela.DETALHAR_USUARIO;
		} catch (SbcuniException sbce) {
			exibirMsgAviso(getMensagem("display.erro.cadastrar.usuario", WebResources.MENSAGEM));
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
