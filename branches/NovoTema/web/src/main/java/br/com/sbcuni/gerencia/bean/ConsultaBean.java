package br.com.sbcuni.gerencia.bean;

import java.util.List;

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

@ViewScoped
@ManagedBean
public class ConsultaBean extends GenericBean {

	private static final long serialVersionUID = 2579627533003774054L;

	public ConsultaBean() {
		super();
	}

	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	private List<Usuario> usuarios;

	private Usuario usuario = new Usuario();
	private Integer tpFiltro;
	
	public String salvarUsuario() {
		try {
			usuarioServiceBean.cadastrarUsuario(usuario);
			exibirMsgInfo(getMensagem("display.usuario.cadastrado.sucesso", WebResources.MENSAGEM));
			WebResources.getFlash().put(WebResources.USUARIO, usuario);
			return Tela.DETALHAR;
		} catch (SbcuniException e) {
			exibirMsgErro(getMensagem(e.getMessage(), WebResources.MENSAGEM));
			return null;
		} catch (Exception e) {
			exibirMsgErro(getMensagem(WebResources.ERRO_INESPERADO, WebResources.MENSAGEM));
			return null;
		}
	}
	
	public String consultarUsuario(){
		try {
			switch (tpFiltro) {
			case Constantes.FILTRO_NOME:
				usuarios = usuarioServiceBean.consultarPorNome(usuario.getNome(), 1);
				if (usuarios.size() > 1) {
					WebResources.getFlash().put(WebResources.LISTA_USUARIOS, usuarios);
					return Tela.LISTA;
				} else if (usuarios.size() == 1) {
					WebResources.getFlash().put(WebResources.USUARIO, usuarios.get(0));
					return Tela.DETALHAR;
				} else if (usuarios.isEmpty()) {
					exibirMsgAviso("display.nenhum.usuario.encontrado");
					return null;
				}
				
			case Constantes.FILTRO_EMAIL:
				usuario = usuarioServiceBean.consultarPorEmail(usuario.getEmail(), 1);
				if (Util.isNull(usuario)) {
					exibirMsgAviso("display.nenhum.usuario.encontrado");
					return null;
				}
				WebResources.getFlash().put(WebResources.USUARIO, usuario);
				return Tela.DETALHAR;
				
			case Constantes.FILTRO_MATRICULA:
				usuario = usuarioServiceBean.consultarPorMatricula(usuario.getMatricula(), 1);
				if (Util.isNull(usuario)) {
					exibirMsgAviso("display.nenhum.usuario.encontrado");
					return null;
				}
				WebResources.getFlash().put(WebResources.USUARIO, usuario);
				return Tela.DETALHAR;
				
			case Constantes.FILTRO_CPF:
				usuario = usuarioServiceBean.consultarPorCpf(Util.retiraMascara(usuario.getCpf()), 1);
				if (Util.isNull(usuario)) {
					exibirMsgAviso("display.nenhum.usuario.encontrado");
					return null;
				}
				WebResources.getFlash().put(WebResources.USUARIO, usuario);
				return Tela.DETALHAR;
				
			case Constantes.FILTRO_PERFIL:
				usuarios = usuarioServiceBean.consultarPorPerfil(usuario.getPerfil());
				if (usuarios.size() > 1) {
					WebResources.getFlash().put(WebResources.LISTA_USUARIOS, usuarios);
					return Tela.LISTA;
				} else if (usuarios.size() == 1) {
					WebResources.getFlash().put(WebResources.USUARIO, usuarios.get(0));
					return Tela.DETALHAR;
				} else if (usuarios.isEmpty()) {
					exibirMsgAviso("display.nenhum.usuario.encontrado");
					return null;
				}
				
			case Constantes.FILTRO_TODOS:
				usuarios = usuarioServiceBean.buscarTodos();
				if (usuarios.isEmpty()) {
					exibirMsgAviso("display.nenhum.usuario.encontrado");
					return null;
				}
				WebResources.getFlash().put(WebResources.LISTA_USUARIOS, usuarios);
				return Tela.LISTA;
			default:
				exibirMsgErro("Selecione um tipo de filtro");
				break;
			}
		} catch (SbcuniException e) {
			exibirMsgAviso(e.getMessage());
			return null;
		}
		return null;
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

	public Integer getTpFiltro() {
		return tpFiltro;
	}

	public void setTpFiltro(Integer tpFiltro) {
		this.tpFiltro = tpFiltro;
	}
}