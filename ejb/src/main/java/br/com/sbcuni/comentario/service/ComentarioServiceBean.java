package br.com.sbcuni.comentario.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.sbcuni.comentario.entity.Comentario;
import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.usuario.entity.Usuario;

@Stateless
public class ComentarioServiceBean implements Serializable {

	private static final long serialVersionUID = -6181845367570577639L;

	@PersistenceContext
	private EntityManager entityManager;

	public void comentar(Comentario comentario) throws SbcuniException {
		try {
			comentario.setTopico(entityManager.find(Topico.class, comentario.getTopico().getIdTopico()));
			comentario.setUsuario(entityManager.find(Usuario.class, comentario.getUsuario().getIdUsuario()));
			entityManager.merge(comentario);
		} catch (Exception e) {
			throw new SbcuniException("Erro ao incluir comentário", e);
		}
	}

	public void excluirComentario(Comentario comentario) throws SbcuniException {
		try {
			comentario = entityManager.find(Comentario.class, comentario.getIdComentario());
			entityManager.remove(comentario);
		} catch (Exception e) {
			throw new SbcuniException("Erro ao incluir comentário", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Comentario> consultarComentariosTopico(Topico topico) {
		Query query = entityManager.createNamedQuery("Comentario.consultarComentariosTopico");
		query.setParameter("idTopico", topico.getIdTopico());
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Long consultarNuComentariosUsuarioGrupoEstudo(Usuario usuario, GrupoEstudo grupoEstudo) {
		Query query = entityManager.createNamedQuery("Comentario.consultarNuComentariosUsuarioGrupoEstudo");
		query.setParameter("idUsuario", usuario.getIdUsuario());
		query.setParameter("idGrupoEstudo", grupoEstudo.getIdGrupoEstudo());
		try {
			return (Long) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Long consultarNuComentariosGrupoEstudo(GrupoEstudo grupoEstudo) {
		Query query = entityManager.createNamedQuery("Comentario.consultarNuComentariosGrupoEstudo");
		query.setParameter("idGrupoEstudo", grupoEstudo.getIdGrupoEstudo());
		try {
			return (Long) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Comentario> consultarComentariosPainelGrupos(GrupoEstudo grupoEstudo) {
		Query query = entityManager.createNamedQuery("Comentario.consultarComentariosPainelGrupos");
		query.setParameter("idGrupoEstudo", grupoEstudo.getIdGrupoEstudo());
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Comentario> consultarComentariosPainel() {
		Query query = entityManager.createNamedQuery("Comentario.consultarComentariosPainel");
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
