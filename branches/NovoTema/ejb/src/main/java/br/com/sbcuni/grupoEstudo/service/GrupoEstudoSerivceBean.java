package br.com.sbcuni.grupoEstudo.service;

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

import br.com.sbcuni.categoria.service.CategoriaServiceBean;
import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.usuario.entity.Usuario;

@Stateless
public class GrupoEstudoSerivceBean implements Serializable {

	private static final long serialVersionUID = -1335213839879763222L;

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private CategoriaServiceBean categoriaServiceBean;

	private StringBuffer queryAlunosGrupoEstudo = new StringBuffer("SELECT u.idUsuario FROM Usuario u WHERE u.idusuario IN (SELECT alunos_idusuario FROM grupoestudo_usuario WHERE grupos_idgrupoestudo = ?) ORDER BY u.nome ASC");

	private StringBuffer queryGruposEstudosUsuario = new StringBuffer("SELECT idgrupoestudo FROM grupoestudo  WHERE idgrupoestudo IN (SELECT grupos_idgrupoestudo FROM grupoestudo_usuario where alunos_idusuario = ?)");

	private StringBuffer queryVerificaUsuarioPertenceGrupo = new StringBuffer("SELECT * FROM grupoestudo_usuario WHERE grupos_idgrupoestudo = ? AND alunos_idusuario = ?");

	public void criarGrupoEstudo(GrupoEstudo grupoEstudo) throws SbcuniException {
		try {
			entityManager.persist(grupoEstudo);
		} catch (Exception e) {
			throw new SbcuniException("Erro ao criar grupod de estudo", e);
		}
	}

	public void alterarGrupoEstudo(GrupoEstudo grupoEstudo) throws SbcuniException {
		try {
			entityManager.merge(grupoEstudo);
		} catch (Exception e) {
			throw new SbcuniException("Erro ao criar grupod de estudo", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<GrupoEstudo> consultarGruposProfessor(Usuario usuario) {
		Query query = entityManager.createNamedQuery("GrupoEstudo.consultarGruposProfessor");
		query.setParameter("idProfessor", usuario.getIdUsuario());
		try {
			List<GrupoEstudo> grupoEstudos = query.getResultList();
			for (GrupoEstudo grupoEstudo : grupoEstudos) {
				grupoEstudo.setAlunos(consultarAlunosGrupoEstudo(grupoEstudo));
				for (Topico topico : grupoEstudo.getTopicosGrupo()) {
					topico.setCategorias(categoriaServiceBean.buscarCategoriaTopico(topico));
				}
			}
			return grupoEstudos;
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<GrupoEstudo> consultarGruposUsuario(Usuario usuario) {
		Query query = entityManager.createNativeQuery(queryGruposEstudosUsuario.toString());
		query.setParameter(1, usuario.getIdUsuario());
		try {
			List<GrupoEstudo> grupoEstudos = new ArrayList<GrupoEstudo>();
			List<BigInteger> ids = query.getResultList();
			for (BigInteger bigInteger : ids) {
				grupoEstudos.add(buscarGrupoEstudoId(bigInteger.longValue()));
			}
			for (GrupoEstudo grupoEstudo : grupoEstudos) {
				grupoEstudo.setAlunos(consultarAlunosGrupoEstudo(grupoEstudo));
				for (Topico topico : grupoEstudo.getTopicosGrupo()) {
					topico.setCategorias(categoriaServiceBean.buscarCategoriaTopico(topico));
				}
			}
			return grupoEstudos;
		} catch (NoResultException e) {
			return null;
		}
	}

	public GrupoEstudo buscarGrupoEstudoId(Long id) {
		Query query = entityManager.createNamedQuery("GrupoEstudo.buscarGrupoEstudoId");
		query.setParameter("idGrupo", id);
		try {
			return (GrupoEstudo) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> consultarAlunosGrupoEstudo(GrupoEstudo grupoEstudo) {
		Query query = entityManager.createNativeQuery(queryAlunosGrupoEstudo.toString());
		query.setParameter(1, grupoEstudo.getIdGrupoEstudo());
		try {
			List<BigInteger> ids = query.getResultList();
			List<Usuario> alunos = new ArrayList<Usuario>();
			for (BigInteger bigInteger : ids) {
				alunos.add(entityManager.find(Usuario.class, bigInteger.longValue()));
			}
			return alunos;
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<GrupoEstudo> pesquisa(String consulta) {
		Query query = entityManager.createNamedQuery("GrupoEstudo.pesquisa");
		query.setParameter("consulta", "%" + consulta.toLowerCase() + "%");
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	public GrupoEstudo consultarGrupoTopico(Topico topico) {
		Query query = entityManager.createNamedQuery("GrupoEstudo.consultarGrupoTopico");
		query.setParameter("idTopico", topico.getIdTopico());
		try {
			return (GrupoEstudo) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Boolean verificaUsuarioPertenceGrupo(GrupoEstudo grupoEstudo, Usuario usuario) {
		Query query = entityManager.createNativeQuery(queryVerificaUsuarioPertenceGrupo.toString());
		query.setParameter(1, grupoEstudo.getIdGrupoEstudo());
		query.setParameter(2, usuario.getIdUsuario());
		try {
			query.getSingleResult();
			return Boolean.TRUE;
		} catch (NoResultException e) {
			return Boolean.FALSE;
		}
	}

	public Boolean verificarUsuarioProfessorGrupo(GrupoEstudo grupoEstudo, Usuario usuario) {
		Query query = entityManager.createNamedQuery("GrupoEstudo.verificarUsuarioProfessorGrupo");
		query.setParameter("idGrupoEstudo", grupoEstudo.getIdGrupoEstudo());
		query.setParameter("idUsuario", usuario.getIdUsuario());
		try {
			query.getSingleResult();
			return Boolean.TRUE;
		} catch (NoResultException e) {
			return Boolean.FALSE;
		}

	}

}
