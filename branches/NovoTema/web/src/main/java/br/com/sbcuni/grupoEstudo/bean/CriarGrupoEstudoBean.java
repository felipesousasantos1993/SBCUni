package br.com.sbcuni.grupoEstudo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Constantes;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.grupoEstudo.service.GrupoEstudoSerivceBean;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.usuario.service.UsuarioServiceBean;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class CriarGrupoEstudoBean extends GenericBean {

	private static final long serialVersionUID = 2926805709691198033L;

	public CriarGrupoEstudoBean() {
		super();
	}
	
	@EJB
	private GrupoEstudoSerivceBean grupoEstudoSerivceBean;
	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	
	private GrupoEstudo grupoEstudo = new GrupoEstudo();
	private List<String> alunos = new ArrayList<String>();
	private List<Usuario> listaAlunos = new ArrayList<Usuario>();
	
	@PostConstruct
	public void init() throws SbcuniException {
		listaAlunos = usuarioServiceBean.consultarPorPerfil(Constantes.PERFIL_ALUNO);
	}
	
	public String criarGrupo() {
		grupoEstudo.setDtCriacao(new Date());
		grupoEstudo.setAlunos(new ArrayList<Usuario>());
		for (String id : alunos) {
			grupoEstudo.getAlunos().add(usuarioServiceBean.consultarUsuarioPorId(Long.valueOf(id)));
		}
		grupoEstudo.setProfessor(UsuarioSessionBean.getInstance().getUsuarioSessao());
		try {
			grupoEstudoSerivceBean.criarGrupoEstudo(grupoEstudo);
			exibirMsgSucesso(getMensagem("display.grupo.criado.sucesso", WebResources.MENSAGEM));
			return Tela.MEUS_GRUPOS;
		} catch (SbcuniException e) {
			exibirMsgErro(e.getMessage());
			return null;
		}
	}

	public GrupoEstudo getGrupoEstudo() {
		return grupoEstudo;
	}

	public void setGrupoEstudo(GrupoEstudo grupoEstudo) {
		this.grupoEstudo = grupoEstudo;
	}

	public List<String> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<String> alunos) {
		this.alunos = alunos;
	}

	public List<Usuario> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<Usuario> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}
	
}
