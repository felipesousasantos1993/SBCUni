package br.com.sbcuni.grupoEstudo.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.avaliacao.service.AvaliacaoServiceBean;
import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.comentario.service.ComentarioServiceBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.grupoEstudo.service.GrupoEstudoSerivceBean;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.topico.service.TopicoServiceBean;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.usuario.service.UsuarioServiceBean;
import br.com.sbcuni.util.RepeatPaginator;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class DetalheGrupoEstudoBean extends GenericBean {

	private static final long serialVersionUID = 1L;

	public DetalheGrupoEstudoBean() {
		super();
	}

	@EJB
	private AvaliacaoServiceBean avaliacaoServiceBean;
	@EJB
	private ComentarioServiceBean comentarioServiceBean;
	@EJB
	private TopicoServiceBean topicoServiceBean;
	@EJB
	private GrupoEstudoSerivceBean grupoEstudoSerivceBean;
	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	
	@PostConstruct
	public void init() {
		grupoEstudo = (GrupoEstudo) WebResources.getFlash().get(WebResources.GRUPO_ESTUDO);
		grupoEstudo.setAlunos(grupoEstudoSerivceBean.consultarAlunosGrupoEstudo(grupoEstudo));
		for (Usuario aluno : grupoEstudo.getAlunos()) {
			aluno.setTopicos(topicoServiceBean.buscarTopicoPorUsuario(aluno.getIdUsuario()));
			aluno.setNuComentariosNoGrupo(comentarioServiceBean.consultarNuComentariosUsuarioGrupoEstudo(aluno, grupoEstudo));
		}
		for (Topico t : grupoEstudo.getTopicosGrupo()) {
			avaliacaoServiceBean.definirAvaliacaoTopico(t);
			t.setComentarios(comentarioServiceBean.consultarComentariosTopico(t));
		}
		repeatPaginatorAlunos = new RepeatPaginator(grupoEstudo.getAlunos());
		repeatPaginatorTopico = new RepeatPaginator(grupoEstudo.getTopicosGrupo());
	}
	
	private GrupoEstudo grupoEstudo;
	private RepeatPaginator repeatPaginatorAlunos;
	private RepeatPaginator repeatPaginatorTopico;
	private Boolean incluirAluno = Boolean.FALSE;
	private String consulta;
 	
	public String telaNovoTopico() {
		WebResources.getFlash().put(WebResources.GRUPO_ESTUDO, grupoEstudo);
		return Tela.NOVO_TOPICO_GRUPO_ESTUDO;
	}
	
	public String telaVisualizarTopico(Topico topico) {
		return detalharTopico(topico);
	}
	
	public String telaIncluirAluno() {
		WebResources.getFlash().put(WebResources.GRUPO_ESTUDO, grupoEstudo);
		return Tela.INCLUIR_ALUNO_PATH;
	}
	
	public void exibirIncluirAlunos() {
		setIncluirAluno(Boolean.TRUE);
	}
	
	public void excluirAlunoGrupo(Usuario usuario) {
		grupoEstudo.getAlunos().remove(usuario);
		repeatPaginatorAlunos = new RepeatPaginator(grupoEstudo.getAlunos());
		try {
			grupoEstudoSerivceBean.alterarGrupoEstudo(grupoEstudo);
		} catch (SbcuniException e) {
			exibirMsgErro(getMensagem("display.erro.excluir.aluno.grupo.estudo", WebResources.MENSAGEM));
		}
	}
	
	public void excluirTopico(Topico topico) {
		grupoEstudo.getTopicosGrupo().remove(topico);
		repeatPaginatorTopico = new RepeatPaginator(grupoEstudo.getTopicosGrupo());
		try {
			grupoEstudoSerivceBean.alterarGrupoEstudo(grupoEstudo);
			exibirMsgSucesso(getMensagem("display.topico.excluido.sucesso", WebResources.MENSAGEM));
		} catch (SbcuniException e) {
			exibirMsgErro(getMensagem("display.erro.ao.excluir.topico", WebResources.MENSAGEM));
		}
	}
	
	public void atualizarListaAlunos() {
		grupoEstudo.setAlunos(new ArrayList<Usuario>(usuarioServiceBean.consultarAlunoNomeOuMatricula(consulta, consulta)));
	}
	
	public void alteraNomeGrupo() {
		try {
			grupoEstudoSerivceBean.alterarGrupoEstudo(grupoEstudo);
		} catch (SbcuniException e) {
			exibirMsgAviso(getMensagem("display.erro.alterar.nome.grupo.estudo", WebResources.MENSAGEM));
		}
	}

	public void marcarAluno(Usuario usuario) {
		usuario.setMarcado(Boolean.TRUE);
	}
	public void desmarcarAluno(Usuario usuario) {
		usuario.setMarcado(Boolean.FALSE);
	}
	
	public void marcarTopico(Topico topico) {
		topico.setMarcado(Boolean.TRUE);
	}
	public void desmarcarTopico(Topico topico) {
		topico.setMarcado(Boolean.FALSE);
	}

	public GrupoEstudo getGrupoEstudo() {
		return grupoEstudo;
	}

	public void setGrupoEstudo(GrupoEstudo grupoEstudo) {
		this.grupoEstudo = grupoEstudo;
	}

	public Boolean getIncluirAluno() {
		return incluirAluno;
	}

	public void setIncluirAluno(Boolean incluirAluno) {
		this.incluirAluno = incluirAluno;
	}

	public RepeatPaginator getRepeatPaginatorAlunos() {
		return repeatPaginatorAlunos;
	}

	public void setRepeatPaginatorAlunos(RepeatPaginator repeatPaginatorAlunos) {
		this.repeatPaginatorAlunos = repeatPaginatorAlunos;
	}

	public RepeatPaginator getRepeatPaginatorTopico() {
		return repeatPaginatorTopico;
	}

	public void setRepeatPaginatorTopico(RepeatPaginator repeatPaginatorTopico) {
		this.repeatPaginatorTopico = repeatPaginatorTopico;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
}