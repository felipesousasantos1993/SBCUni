package br.com.sbcuni.topico.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.sbcuni.categoria.entity.Categoria;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.usuario.entity.Usuario;

@Stateless
public class TopicoServiceBean implements Serializable {

	private static final long serialVersionUID = 5622487242178087343L;

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Método : postartopico Descrição: Inclui o topico.
	 * 
	 * @param topico
	 * 
	 */
	public void postarTopico(Topico topico) {
		List<Categoria> auxLista = new ArrayList<Categoria>();
		for (Categoria categoria : topico.getCategorias()) {
			auxLista.add(entityManager.find(Categoria.class, categoria.getIdCategoria()));
		}
		topico.setCategorias(new ArrayList<Categoria>(auxLista));
		topico.setUsuario(entityManager.find(Usuario.class, topico.getUsuario().getIdUsuario()));
		entityManager.persist(topico);
	}

	/***
	 * Método   : buscarPostagensPorUsuario
	 * Descrição: 
	 * @param idUsuario
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Topico> buscarTopicoPorUsuario(Long idUsuario) {
		Query query = entityManager.createNamedQuery("Topico.buscarTopicosPorUsuario");
		query.setParameter("idUsuario", idUsuario);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * Método : listarTodasCategorias Descrição: Retorna a lista de todas as
	 * categorias no banco.
	 * 
	 * @return Lista<Categoria>
	 */
	@SuppressWarnings("unchecked")
	public List<Categoria> listarTodasCategorias() {
		try {
			Query query = entityManager.createNamedQuery("Categoria.listarTodasCategorias");
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Topico> buscarTodosTopicos() {
		Query query = entityManager.createNamedQuery("Topico.buscarTodosTopicos");
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	public List<Topico> buscarTopicosTituloDescricao(String titulo, String descricao) {
		Query query = entityManager.createNamedQuery("Topico.buscarTopicosTituloDescricao");
		query.setParameter("titulo", "%" + titulo.toLowerCase() + "%");
		query.setParameter("descricao", "%" + descricao.toLowerCase() + "%");
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	/**
	 * @param topico
	 */
	public void alterarTopico(Topico topico) {
		entityManager.merge(topico);
	}
	/**
	 * @param topico
	 */
	public void excluirTopico(Topico topico) {
		topico = entityManager.find(Topico.class, topico.getIdTopico());
		entityManager.remove(topico);
	}

	public List<Topico> buscarTopicosGrupo(GrupoEstudo grupoEstudo) {
		Query query = entityManager.createNamedQuery("Topico.buscarTopicosGrupo");
		query.setParameter("idGrupoEstudo", grupoEstudo.getIdGrupoEstudo());
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
}
