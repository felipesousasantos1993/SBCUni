package br.com.sbcuni.mensagem.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
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

	@PersistenceContext
	private EntityManager entityManager;
	
	private StringBuffer queryMensagemCaixaEntrada = new StringBuffer("SELECT id FROM mensagem  WHERE remetente_idusuario <> ? AND id IN (select msgsrecebidas_id FROM mensagem_usuario WHERE destinatarios_idusuario = ?)");
	
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
	
	public List<Mensagem> consultarCaixaEntradaUsuario(Usuario usuario) {
		Query query = entityManager.createNativeQuery(queryMensagemCaixaEntrada.toString());
		query.setParameter(1, usuario.getIdUsuario());
		query.setParameter(2, usuario.getIdUsuario());
		try {
			List<BigInteger> ids = query.getResultList();
			List<Mensagem> mensagens = new ArrayList<Mensagem>();
			for (BigInteger bigInteger : ids) {
				mensagens.add(consultarMensagemPorId(bigInteger.longValue()));
			}
			return mensagens;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Mensagem> consultarMensagemEnviadasUsuario(Usuario usuario) {
		Query query = entityManager.createNamedQuery("Mensagem.consultarMensagemEnviadasUsuario");
		query.setParameter("idUsuario", usuario.getIdUsuario());
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
