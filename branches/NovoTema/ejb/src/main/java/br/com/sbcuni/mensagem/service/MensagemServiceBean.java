package br.com.sbcuni.mensagem.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.sbcuni.mensagem.entity.Mensagem;
import br.com.sbcuni.usuario.entity.Usuario;

@Stateless
public class MensagemServiceBean implements Serializable {

	private static final long serialVersionUID = 8645403616457246100L;

	private static final Integer MSG_PRINCIPAL = 1;
	private static final Integer MSG_ENVIADA = 2;
	private static final Integer MSG_EXCLUIDA = 3;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void enviarMensagem(Mensagem mensagem) {
		mensagem.setRemetente(entityManager.find(Usuario.class, mensagem.getRemetente().getIdUsuario()));
		entityManager.merge(mensagem);
	}
	
	public Mensagem consultarMensagemPorId(Long idMensagem) {
		Query query = entityManager.createNamedQuery("Mensagem.consultarMensagemPorId");
		query.setParameter("idMensagem", idMensagem);
		try {
			return (Mensagem) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Mensagem> consultarRecebidas(Usuario usuario) {
		Query query = entityManager.createNamedQuery("Mensagem.consultarRecebidas");
		query.setParameter("idDestinatario", usuario.getIdUsuario());
		query.setParameter("idTipo", MSG_PRINCIPAL);
		try {
			return  query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Mensagem> consultarRecebidasLixeira(Usuario usuario) {
		Query query = entityManager.createNamedQuery("Mensagem.consultarRecebidas");
		query.setParameter("idDestinatario", usuario.getIdUsuario());
		query.setParameter("idTipo", MSG_EXCLUIDA);
		try {
			return  query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	public List<Mensagem> consultarEnviadasLixeira(Usuario usuario) {
		Query query = entityManager.createNamedQuery("Mensagem.consultarEnviadas");
		query.setParameter("idUsuario", usuario.getIdUsuario());
		query.setParameter("idTipo", MSG_EXCLUIDA);
		try {
			return  query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Mensagem> consultarEnviadas(Usuario usuario) {
		Query query = entityManager.createNamedQuery("Mensagem.consultarEnviadas");
		query.setParameter("idUsuario", usuario.getIdUsuario());
		query.setParameter("idTipo", MSG_ENVIADA);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Mensagem> pesquisa(String consulta, Usuario usuario) {
		Query query = entityManager.createNamedQuery("Mensagem.pesquisa");
		query.setParameter("consulta", "%" + consulta.toLowerCase() + "%");
		query.setParameter("idUsuario", usuario.getIdUsuario());
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	public List<Mensagem> consultarMensagemNotificacao(Date dtUltimoAcesso, Long idUsuario) {
		Query query = entityManager.createNamedQuery("Mensagem.consultarMensagemNotificacao");
		query.setParameter("dtUltimoAcesso", dtUltimoAcesso);
		query.setParameter("idUsuario", idUsuario);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
