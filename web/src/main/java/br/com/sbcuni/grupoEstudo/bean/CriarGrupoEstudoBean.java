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
import br.com.sbcuni.util.RepeatPaginator;
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
	private List<Usuario> listaAlunos = new ArrayList<Usuario>();
	private RepeatPaginator repeatPaginator;
	private String consulta;
	
	@PostConstruct
	public void init() throws SbcuniException {
		listaAlunos = usuarioServiceBean.consultarPorPerfil(Constantes.PERFIL_ALUNO);
		repeatPaginator = new RepeatPaginator(listaAlunos);
	}
	
	public String criarGrupo() {
		grupoEstudo.setDtCriacao(new Date());
		grupoEstudo.setAlunos(new ArrayList<Usuario>());
		for (Usuario a : listaAlunos) {
			if(a.getMarcado()) {
				grupoEstudo.getAlunos().add(a);
			}
		}
		if (grupoEstudo.getAlunos().isEmpty()) {
			exibirMsgAviso("É necessário incluir pelo menos 1(um) aluno ao grupo de estudo");
			return null;
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
	
	public void atualizarAlunos() {
		listaAlunos = usuarioServiceBean.consultarAlunoNomeOuMatricula(consulta, consulta);
		repeatPaginator = new RepeatPaginator(listaAlunos);
	}
	
	public void marcarAluno(Usuario usuario) {
		usuario.setMarcado(Boolean.TRUE);
	}
	public void desmarcarAluno(Usuario usuario) {
		usuario.setMarcado(Boolean.FALSE);
	}

	public GrupoEstudo getGrupoEstudo() {
		return grupoEstudo;
	}

	public void setGrupoEstudo(GrupoEstudo grupoEstudo) {
		this.grupoEstudo = grupoEstudo;
	}

	public List<Usuario> getListaAlunos() {
		return listaAlunos;
	}

	public void setListaAlunos(List<Usuario> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	public RepeatPaginator getRepeatPaginator() {
		return repeatPaginator;
	}

	public void setRepeatPaginator(RepeatPaginator repeatPaginator) {
		this.repeatPaginator = repeatPaginator;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	
}
