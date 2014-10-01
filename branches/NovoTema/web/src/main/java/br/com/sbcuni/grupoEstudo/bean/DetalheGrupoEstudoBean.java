package br.com.sbcuni.grupoEstudo.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.avaliacao.service.AvaliacaoServiceBean;
import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.comentario.service.ComentarioServiceBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.topico.service.TopicoServiceBean;
import br.com.sbcuni.usuario.entity.Usuario;
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
	
	@PostConstruct
	public void init() {
		grupoEstudo = (GrupoEstudo) WebResources.getFlash().get(WebResources.GRUPO_ESTUDO);
		for (Usuario aluno : grupoEstudo.getAlunos()) {
			aluno.setTopicos(topicoServiceBean.buscarTopicoPorUsuario(aluno.getIdUsuario()));
			aluno.setNuComentariosNoGrupo(comentarioServiceBean.consultarNuComentariosUsuarioGrupoEstudo(aluno, grupoEstudo));
		}
		for (Topico t : grupoEstudo.getTopicosGrupo()) {
			avaliacaoServiceBean.definirAvaliacaoTopico(t);
			t.setComentarios(comentarioServiceBean.consultarComentariosTopico(t));
		}
	}
	
	private GrupoEstudo grupoEstudo;
	
	public String telaNovoTopico() {
		WebResources.getFlash().put(WebResources.GRUPO_ESTUDO, grupoEstudo);
		return Tela.NOVO_TOPICO_GRUPO_ESTUDO;
	}
	
	public void excluirAlunoGrupo(Usuario usuario) {
		
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
	
}
