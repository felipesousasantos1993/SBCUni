package br.com.sbcuni.avaliacao.service;

import java.io.Serializable;
import java.math.BigInteger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.sbcuni.avaliacao.entity.Avaliacao;
import br.com.sbcuni.comentario.entity.Comentario;
import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.usuario.entity.Usuario;

@Stateless
public class AvaliacaoServiceBean implements Serializable {

	private static final long serialVersionUID = -5683593004515400565L;

	@PersistenceContext
	private EntityManager entityManager;
	
	private StringBuffer queryNuAvaliacoesPositivasComentario = new StringBuffer("SELECT COUNT(*) FROM avaliacao WHERE avaliacao = true and idavaliacao IN (SELECT  avaliacoes_idavaliacao from avaliacao_comentario  WHERE comentarios_idcomentario  = ?)");
	private StringBuffer queryNuAvaliacoesNegativasComentario = new StringBuffer("SELECT COUNT(*) FROM avaliacao WHERE avaliacao = false and idavaliacao IN (SELECT  avaliacoes_idavaliacao from avaliacao_comentario  WHERE comentarios_idcomentario  = ?)");
	
	private StringBuffer queryNuAvaliacoesPositivasTopico = new StringBuffer("SELECT COUNT(*) FROM avaliacao WHERE avaliacao = true and idavaliacao IN (SELECT  avaliacaos_idavaliacao from avaliacao_topico  WHERE topicos_idtopico  = ?)");
	private StringBuffer queryNuAvaliacoesNegativasTopico = new StringBuffer("SELECT COUNT(*) FROM avaliacao WHERE avaliacao = false and idavaliacao IN (SELECT  avaliacaos_idavaliacao from avaliacao_topico  WHERE topicos_idtopico  = ?)");
	
	private StringBuffer queryVerificaVotoUsuarioTopico = new StringBuffer("SELECT a.idavaliacao FROM avaliacao a INNER JOIN avaliacao_topico ap ON ap.avaliacaos_idavaliacao = a.idavaliacao WHERE a.usuario = ? AND ? IN (ap.topicos_idtopico)");
	private StringBuffer queryVerificaVotoUsuarioComentario = new StringBuffer("SELECT a.idavaliacao FROM avaliacao a INNER JOIN avaliacao_comentario ac ON ac.avaliacoes_idavaliacao = a.idavaliacao WHERE a.usuario = ? AND ? IN (ac.comentarios_idcomentario)");
	
	public void avaliar(Avaliacao avaliacao) throws SbcuniException {
		try {
			entityManager.merge(avaliacao);
		} catch (Exception e) {
			throw new SbcuniException("Erro ao avaliar", e);
		}
	}
	
	public void definirAvaliacaoTopico(Topico topico) {
		nuAvaliacoesNegativasTopico(topico);
		nuAvaliacoesPositivasTopico(topico);
	}

	public void definirAvaliacaoComentario(Comentario comentario) {
		nuAvaliacoesPositivasComentario(comentario);
		nuAvaliacoesNegativasComentario(comentario);
	}
	
	public void nuAvaliacoesPositivasTopico(Topico topico) {
		Query query = entityManager.createNativeQuery(queryNuAvaliacoesPositivasTopico.toString());
		query.setParameter(1, topico.getIdTopico());
		topico.setNuAvaliacaoPositivas((BigInteger) query.getSingleResult());
	}
	public void nuAvaliacoesNegativasTopico(Topico topico) {
		Query query = entityManager.createNativeQuery(queryNuAvaliacoesNegativasTopico.toString());
		query.setParameter(1, topico.getIdTopico());
		topico.setNuAvaliacaoNegativas((BigInteger) query.getSingleResult());
	}
	
	public void nuAvaliacoesPositivasComentario(Comentario comentario) {
		Query query = entityManager.createNativeQuery(queryNuAvaliacoesPositivasComentario.toString());
		query.setParameter(1, comentario.getIdComentario());
		comentario.setNuAvaliacaoPositivas((BigInteger) query.getSingleResult());
	}
	public void nuAvaliacoesNegativasComentario(Comentario comentario) {
		Query query = entityManager.createNativeQuery(queryNuAvaliacoesNegativasComentario.toString());
		query.setParameter(1, comentario.getIdComentario());
		comentario.setNuAvaliacaoNegativas((BigInteger) query.getSingleResult());
	}
	
	
	public Avaliacao verificarAvaliacaoUsuarioTopico(Usuario usuario, Topico topico) {
		try {
			Query query = entityManager.createNativeQuery(queryVerificaVotoUsuarioTopico.toString());
			query.setParameter(1, usuario.getIdUsuario());
			query.setParameter(2, topico.getIdTopico());
			BigInteger idAvaliacao = (BigInteger) query.getSingleResult();
			return entityManager.find(Avaliacao.class, idAvaliacao.longValue());
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Avaliacao verificarAvaliacaoUsuarioComentario(Usuario usuario, Comentario comentario) {
		try {
			Query query = entityManager.createNativeQuery(queryVerificaVotoUsuarioComentario.toString());
			query.setParameter(1, usuario.getIdUsuario());
			query.setParameter(2, comentario.getIdComentario());
			BigInteger idAvaliacao = (BigInteger) query.getSingleResult();
			return entityManager.find(Avaliacao.class, idAvaliacao.longValue());
		} catch (NoResultException e) {
			return null;
		}
	}

	public void removerAvaliacao(Avaliacao avaliacao) {
		avaliacao = entityManager.find(Avaliacao.class, avaliacao.getIdAvaliacao());
		entityManager.remove(avaliacao);
	}
	
}
