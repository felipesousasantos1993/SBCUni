package br.com.sbcuni.aluno.bean;

import java.util.List;

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

@ViewScoped
@ManagedBean
public class ConsultarUsuarioBean extends GenericBean {

	private static final long serialVersionUID = 9084169429056228484L;

	public ConsultarUsuarioBean() {
		super();
	}
	private Usuario usuario = new Usuario();
	
	private Integer tpFiltro;
	
	private List<Usuario> usuarios;
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	@EJB
	private UsuarioServiceBean usuarioServiceBean;

	@PostConstruct
	public void init() {
		usuario.setPerfil((Integer) WebResources.getFlash().get(WebResources.PERFIL));
	}
	
	public String consultarUsuario(){
		Usuario usuarioConsulta = new Usuario();
		try {
			WebResources.getFlash().put(WebResources.PERFIL, usuario.getPerfil());
			switch (tpFiltro) {
			case Constantes.FILTRO_NOME:
				usuarios = usuarioServiceBean.consultarPorNome(usuario.getNome(), usuario.getPerfil());
				if (usuarios.size() > 1) {
					WebResources.getFlash().put(WebResources.LISTA_USUARIOS, usuarios);
					return Tela.LISTAR_USUARIO;
				} else if (usuarios.size() == 1) {
					WebResources.getFlash().put(WebResources.USUARIO, usuarios.get(0));
					return Tela.DETALHAR_USUARIO;
				} else if (usuarios.isEmpty()) {
					exibirMsgAviso(getMensagem("display.nenhum.usuario.encontrado", WebResources.MENSAGEM));
					return null;
				}
				
			case Constantes.FILTRO_EMAIL:
				usuarioConsulta = usuarioServiceBean.consultarPorEmail(usuario.getEmail(), usuario.getPerfil());
				if (Util.isNull(usuarioConsulta)) {
					exibirMsgAviso(getMensagem("display.nenhum.usuario.encontrado", WebResources.MENSAGEM));
					return null;
				}
				WebResources.getFlash().put(WebResources.USUARIO, usuarioConsulta);
				return Tela.DETALHAR_USUARIO;
				
			case Constantes.FILTRO_MATRICULA:
				usuarioConsulta = usuarioServiceBean.consultarPorMatricula(usuario.getMatricula(), usuario.getPerfil());
				if (Util.isNull(usuarioConsulta)) {
					exibirMsgAviso(getMensagem("display.nenhum.usuario.encontrado", WebResources.MENSAGEM));
					return null;
				}
				WebResources.getFlash().put(WebResources.USUARIO, usuarioConsulta);
				return Tela.DETALHAR_USUARIO;
				
			case Constantes.FILTRO_CPF:
				usuarioConsulta = usuarioServiceBean.consultarPorCpf(Util.retiraMascara(usuario.getCpf()), usuario.getPerfil());
				if (Util.isNull(usuarioConsulta)) {
					exibirMsgAviso(getMensagem("display.nenhum.usuario.encontrado", WebResources.MENSAGEM));
					return null;
				}
				WebResources.getFlash().put(WebResources.USUARIO, usuarioConsulta);
				return Tela.DETALHAR_USUARIO;
				
			case Constantes.FILTRO_TODOS:
				usuarios = usuarioServiceBean.consultarPorPerfil(usuario.getPerfil());
				if (usuarios.isEmpty()) {
					exibirMsgAviso(getMensagem("display.nenhum.usuario.encontrado", WebResources.MENSAGEM));
					return null;
				}
				WebResources.getFlash().put(WebResources.LISTA_USUARIOS, usuarios);
				return Tela.LISTAR_USUARIO;
			default:
				exibirMsgErro("Selecione um tipo de filtro");
				break;
			}
		} catch (SbcuniException e) {
			exibirMsgAviso(getMensagem("display.erro.consultar.usuario", WebResources.MENSAGEM));
			return null;
		}
		return null;
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
