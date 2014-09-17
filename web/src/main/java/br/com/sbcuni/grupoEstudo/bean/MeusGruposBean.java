package br.com.sbcuni.grupoEstudo.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Constantes;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.grupoEstudo.service.GrupoEstudoSerivceBean;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class MeusGruposBean extends GenericBean {

	private static final long serialVersionUID = -4806270298918532303L;

	public MeusGruposBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		if (UsuarioSessionBean.getInstance().getUsuarioSessao().getPerfil().equals(Constantes.PERFIL_ALUNO)) {
			
		} else {
			grupoEstudos = grupoEstudoSerivceBean.consultarGruposProfessor(UsuarioSessionBean.getInstance().getUsuarioSessao());
		}
	}
	
	@EJB
	private GrupoEstudoSerivceBean grupoEstudoSerivceBean;
	
	private List<GrupoEstudo> grupoEstudos;
	
	public String detalharGrupoEstudo(GrupoEstudo grupoEstudo) {
		WebResources.getFlash().put(WebResources.GRUPO_ESTUDO, grupoEstudo);
		return Tela.DETALHE_GRUPO_ESTUDO;
	}

	public List<GrupoEstudo> getGrupoEstudos() {
		return grupoEstudos;
	}

	public void setGrupoEstudos(List<GrupoEstudo> grupoEstudos) {
		this.grupoEstudos = grupoEstudos;
	}
	
	
	
}
