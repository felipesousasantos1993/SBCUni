package br.com.sbcuni.categoria.service;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.sbcuni.categoria.entity.Categoria;
import br.com.sbcuni.topico.entity.Topico;

@Stateless
public class CategoriaServiceBean implements Serializable {

	private static final long serialVersionUID = -2989494896608756265L;

	@PersistenceContext
	private EntityManager entityManager;
	
	private StringBuffer queryBuscarCategoriaTopico = new StringBuffer("SELECT c.idCategoria FROM Categoria c WHERE c.idCategoria IN (SELECT categorias_idcategoria FROM topico_categoria WHERE topicos_idtopico =?) ORDER BY c.deCategoria ASC");
	
	@SuppressWarnings("unchecked")
	public List<Categoria> buscarCategoriaTopico(Topico topico) {
		Query query = entityManager.createNativeQuery(queryBuscarCategoriaTopico.toString());
		query.setParameter(1, topico.getIdTopico());
		try {
			List<BigInteger> ids  = query.getResultList();
			List<Categoria> categorias = new ArrayList<Categoria>();
			for (BigInteger bigInteger : ids) {
				categorias.add(entityManager.find(Categoria.class, bigInteger.longValue()));
			}
			return categorias;
		} catch (NoResultException e) {
			return null;
		}
	}
	
}
