package br.com.sbcuni.usuario.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.usuario.service.UsuarioServiceBean;
import br.com.sbcuni.util.Util;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class EditarPerfilBean extends GenericBean {

	private static final long serialVersionUID = 9039215126315591384L;
	
	public EditarPerfilBean() {
		super();
	}
	
	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	
	private Usuario usuario;
	
	private List<SelectItem> listaEstados = new ArrayList<SelectItem>();
	
	@PostConstruct
	public void init() {
		usuario = UsuarioSessionBean.getInstance().getUsuarioSessao();
		popularListasEstados();
	}
	
	public void popularListasEstados() {
		listaEstados.add(new SelectItem("AC", "AC"));
		listaEstados.add(new SelectItem("RJ", "RJ"));
		listaEstados.add(new SelectItem("SP", "SP"));
	}
	
	public String alterarPerfil() {
		try {
			usuario.setCpf(Util.retiraMascara(usuario.getCpf()));
			usuarioServiceBean.alterarUsuario(usuario);
			UsuarioSessionBean.getInstance().removerSessao();
			UsuarioSessionBean.getInstance().iniciarSessao(WebResources.USUARIO, usuario);
			exibirMsgInfo("Perfil atualizado");
			WebResources.getFlash().put(WebResources.USUARIO, usuario);
			return Tela.PEFIL_PATH;
		} catch (Exception e) {
			exibirMsgErro("Erro ao editar perfil!");
			return null;
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

	public List<SelectItem> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<SelectItem> listaEstados) {
		this.listaEstados = listaEstados;
	}
	
}
