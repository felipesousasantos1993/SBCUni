package br.com.sbcuni.grupoEstudo.bean;

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
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.usuario.service.UsuarioServiceBean;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class IncluirAlunosBean extends GenericBean {

	private static final long serialVersionUID = -8107014551643247255L;

	public IncluirAlunosBean() {
		super();
	}
	
	private GrupoEstudo grupoEstudo;

	private List<Usuario> alunos;
	private String consulta;
	
	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	@EJB
	private GrupoEstudoSerivceBean grupoEstudoSerivceBean;
	
	@PostConstruct
	public void init() {
		grupoEstudo = (GrupoEstudo) WebResources.getFlash().get(WebResources.GRUPO_ESTUDO);
		alunos = usuarioServiceBean.consultarPorPerfil(Constantes.PERFIL_ALUNO);
		for (Usuario aluno : grupoEstudo.getAlunos()) {
			alunos.remove(aluno);
		}
		for (Usuario a : alunos) {
			a.setPertenceGrupo(Boolean.FALSE);
		}
	}
	
	public String incluirAluno(Usuario usuario) {
		try {
			grupoEstudo.getAlunos().add(usuario);
			grupoEstudoSerivceBean.alterarGrupoEstudo(grupoEstudo);
			exibirMsgSucesso(getMensagem("display.alunos.incluidos.sucesso", WebResources.MENSAGEM));
			usuario.setPertenceGrupo(Boolean.TRUE);
			WebResources.getFlash().put(WebResources.GRUPO_ESTUDO, grupoEstudo);
			return Tela.DETALHE_GRUPO_ESTUDO_PATH;
		} catch (SbcuniException e) {
			exibirMsgAviso(getMensagem("display.erro.incluir.aluno.grupo.estudo", WebResources.MENSAGEM));
			return null;
		}
	}
	
	public void atualizarAlunos() {
		alunos = usuarioServiceBean.consultarAlunoNomeOuMatricula(consulta, consulta);
		for (Usuario aluno : grupoEstudo.getAlunos()) {
			alunos.remove(aluno);
		}
	}
	
	public String voltarTelaDetalharGrupo() {
		WebResources.getFlash().put(WebResources.GRUPO_ESTUDO, grupoEstudo);
		return Tela.DETALHE_GRUPO_ESTUDO_PATH;
	}

	public GrupoEstudo getGrupoEstudo() {
		return grupoEstudo;
	}

	public void setGrupoEstudo(GrupoEstudo grupoEstudo) {
		this.grupoEstudo = grupoEstudo;
	}

	public List<Usuario> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Usuario> alunos) {
		this.alunos = alunos;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	
	
}
