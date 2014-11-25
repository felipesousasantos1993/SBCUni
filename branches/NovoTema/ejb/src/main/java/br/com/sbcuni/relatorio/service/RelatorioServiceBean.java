package br.com.sbcuni.relatorio.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.grupoEstudo.service.GrupoEstudoSerivceBean;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.usuario.entity.Usuario;

@Stateless
public class RelatorioServiceBean implements Serializable {

	private static final long serialVersionUID = -7722847215305839466L;

	@PersistenceContext
	private EntityManager entityManager;
	
	@EJB
	private GrupoEstudoSerivceBean grupoEstudoSerivceBean;

	private StringBuffer queryTopicosBemAvaliados = new StringBuffer("SELECT t.idtopico, count(a) FROM topico  t, avaliacao a, avaliacao_topico atopico WHERE t.idtopico = atopico.topicos_idtopico AND a.idavaliacao = atopico.avaliacaos_idavaliacao AND a.avaliacao = true GROUP BY t.idtopico ORDER BY count(a) DESC");
	private StringBuffer queryTopicosMalAvaliados = new StringBuffer("SELECT t.idtopico, count(a) FROM topico  t, avaliacao a, avaliacao_topico atopico WHERE t.idtopico = atopico.topicos_idtopico AND a.idavaliacao = atopico.avaliacaos_idavaliacao AND a.avaliacao = false GROUP BY t.idtopico ORDER BY count(a) DESC");
	private StringBuffer queryTopicosMaisComentados = new StringBuffer("SELECT t.idtopico, count(c) as comentarios FROM topico t INNER JOIN comentario c ON t.idtopico = c.topico GROUP BY t.idtopico ORDER BY comentarios DESC");
	private StringBuffer queryUsuariosComMaisTopicos = new StringBuffer("SELECT u.idusuario, count(t) as topicos FROM usuario u INNER JOIN topico t ON t.usuario = u.idusuario GROUP BY u.idusuario ORDER BY topicos DESC");
	private StringBuffer queryUsuariosComMaisComentarios = new StringBuffer("SELECT u.idusuario, count(c) as comentarios FROM usuario u INNER JOIN comentario c ON c.usuario = u.idusuario GROUP BY u.idusuario ORDER BY comentarios DESC");
	private StringBuffer queryGruposComMaisTopicos = new StringBuffer("SELECT g.idgrupoestudo, count(t) as topicos FROM grupoestudo g INNER JOIN topico t ON t.grupoestudo_idgrupoestudo = g.idgrupoestudo GROUP BY g.idgrupoestudo ORDER BY topicos DESC");
	private StringBuffer queryGruposComMaisAlunos = new StringBuffer("SELECT 	g.idgrupoestudo as grupo, count(u) as alunos FROM grupoestudo g INNER JOIN grupoestudo_usuario gu ON gu.grupos_idgrupoestudo = g.idgrupoestudo INNER JOIN usuario u ON gu.alunos_idusuario = u.idusuario GROUP BY grupo ORDER BY alunos DESC");
	
	
	public List<Topico> buscarTopicosMaisBemAvaliados() {
		Query query = entityManager.createNativeQuery(queryTopicosBemAvaliados.toString());
		try {
			return converterRetornoPositivo(query.getResultList());
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Topico> buscarTopicosMaisComentados() {
		Query query = entityManager.createNativeQuery(queryTopicosMaisComentados.toString());
		try { 
			return converterRetornoMaisComentados(query.getResultList());
		} catch (NoResultException e) {
			return null;
		}
	}
	public List<Usuario> buscarUsuariosComMaisTopicos() {
		Query query = entityManager.createNativeQuery(queryUsuariosComMaisTopicos.toString());
		try { 
			return converterRetornoUsuariosComMaisTopicos(query.getResultList());
		} catch (NoResultException e) {
			return null;
		}
	}
	public List<Usuario> buscarUsuariosComMaisComentarios() {
		Query query = entityManager.createNativeQuery(queryUsuariosComMaisComentarios.toString());
		try { 
			return converterRetornoUsuariosComMaisComentarios(query.getResultList());
		} catch (NoResultException e) {
			return null;
		}
	}
	public List<GrupoEstudo> buscarGruposComMaisTopicos() {
		Query query = entityManager.createNativeQuery(queryGruposComMaisTopicos.toString());
		try { 
			return converterRetornoGruposComMaisTopicos(query.getResultList());
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<GrupoEstudo> buscarGruposComMaisAlunos() {
		Query query = entityManager.createNativeQuery(queryGruposComMaisAlunos.toString());
		try { 
			return converterRetornoGruposComMaisAlunos(query.getResultList());
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Topico> buscarTopicosMaisMalAvaliados() {
		Query query = entityManager.createNativeQuery(queryTopicosMalAvaliados.toString());
		try {
			return converterRetornoNegativas(query.getResultList());
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<GrupoEstudo> converterRetornoGruposComMaisAlunos(List<Object[]> lista) {
		List<GrupoEstudo> grupos = new ArrayList<GrupoEstudo>();
		for (Object[] objects : lista) {
			GrupoEstudo grupoEstudo = grupoEstudoSerivceBean.buscarGrupoEstudoId(Long.valueOf(objects[0].toString()));
			grupoEstudo.setNuAlunos(BigInteger.valueOf(Long.valueOf(objects[1].toString())));
			grupos.add(grupoEstudo);
		}
		return grupos;
	}
	
	public List<GrupoEstudo> converterRetornoGruposComMaisTopicos(List<Object[]> lista) {
		List<GrupoEstudo> grupos = new ArrayList<GrupoEstudo>();
		for (Object[] objects : lista) {
			GrupoEstudo grupoEstudo = grupoEstudoSerivceBean.buscarGrupoEstudoId(Long.valueOf(objects[0].toString()));
			grupoEstudo.setNuTopicosAux(BigInteger.valueOf(Long.valueOf(objects[1].toString())));
			grupos.add(grupoEstudo);
		}
		return grupos;
	}
	
	public List<Usuario> converterRetornoUsuariosComMaisTopicos(List<Object[]> lista) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for (Object[] objects : lista) {
			Usuario usuario = entityManager.find(Usuario.class,(Long.valueOf(objects[0].toString())));
			usuario.setNuTopicosCriados(BigInteger.valueOf(Long.valueOf(objects[1].toString())));
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	public List<Usuario> converterRetornoUsuariosComMaisComentarios(List<Object[]> lista) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		for (Object[] objects : lista) {
			Usuario usuario = entityManager.find(Usuario.class,(Long.valueOf(objects[0].toString())));
			usuario.setNuComentariosCriados(BigInteger.valueOf(Long.valueOf(objects[1].toString())));
			usuarios.add(usuario);
		}
		return usuarios;
	}

	
	public List<Topico> converterRetornoMaisComentados(List<Object[]> lista) {
		List<Topico> topicos = new ArrayList<Topico>();
		for (Object[] objects : lista) {
			Topico topico = buscarTopicoPorId(Long.valueOf(objects[0].toString()));
			topico.setNuComentariosAux(BigInteger.valueOf(Long.valueOf(objects[1].toString())));
			topicos.add(topico);
		}
		return topicos;
	}

	public List<Topico> converterRetornoPositivo(List<Object[]> lista) {
		List<Topico> topicos = new ArrayList<Topico>();
		for (Object[] objects : lista) {
			Topico topico = buscarTopicoPorId(Long.valueOf(objects[0].toString()));
			topico.setNuAvaliacaoPositivas(BigInteger.valueOf(Long.valueOf(objects[1].toString())));
			topicos.add(topico);
		}
		return topicos;
	}

	public List<Topico> converterRetornoNegativas(List<Object[]> lista) {
		List<Topico> topicos = new ArrayList<Topico>();
		for (Object[] objects : lista) {
			Topico topico = buscarTopicoPorId(Long.valueOf(objects[0].toString()));
			topico.setNuAvaliacaoNegativas(BigInteger.valueOf(Long.valueOf(objects[1].toString())));
			topicos.add(topico);
		}
		return topicos;
	}

	public Topico buscarTopicoPorId(Long idTopico) {
		Query query = entityManager.createNamedQuery("Topico.buscarTopicosPorId");
		query.setParameter("idTopico", idTopico);
		try {
			return (Topico) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Topico> buscarTopicosMaisVisualizados(List<Long> listaGrupos) {
		Query query;
		if (listaGrupos.isEmpty()) {
			query = entityManager.createNamedQuery("Topico.buscarTopicosMaisVisualizados");
		} else {
			query = entityManager.createNamedQuery("Topico.buscarTopicosMaisVisualizadosGrupo");
			query.setParameter("listaGrupos", listaGrupos);
		}
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
