package br.com.sbcuni.grupoEstudo.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.avaliacao.service.AvaliacaoServiceBean;
import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.comentario.service.ComentarioServiceBean;
import br.com.sbcuni.constantes.Constantes;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.grupoEstudo.service.GrupoEstudoSerivceBean;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;

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
			grupoEstudos = grupoEstudoSerivceBean.consultarGruposUsuario(UsuarioSessionBean.getInstance().getUsuarioSessao());
		} else {
			grupoEstudos = grupoEstudoSerivceBean.consultarGruposProfessor(UsuarioSessionBean.getInstance().getUsuarioSessao());
		}
		
		for (GrupoEstudo ge : grupoEstudos) {
			ge.setNuComentariosGrupo(comentarioServiceBean.consultarNuComentariosGrupoEstudo(ge));
			ge.setNuAvaliacoesPositivas(avaliacaoServiceBean.nuAvaliacoesPositivasGrupoEstudo(ge));
			ge.setNuAvaliacoesNegativas(avaliacaoServiceBean.nuAvaliacoesNegativasGrupoEstudo(ge));
		}
	}
	
	@EJB
	private GrupoEstudoSerivceBean grupoEstudoSerivceBean;
	@EJB
	private ComentarioServiceBean comentarioServiceBean;
	@EJB
	private AvaliacaoServiceBean avaliacaoServiceBean;
	
	private List<GrupoEstudo> grupoEstudos;
	

	public List<GrupoEstudo> getGrupoEstudos() {
		return grupoEstudos;
	}

	public void setGrupoEstudos(List<GrupoEstudo> grupoEstudos) {
		this.grupoEstudos = grupoEstudos;
	}
	
	
	
}
