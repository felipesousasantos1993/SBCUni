package br.com.sbcuni.usuario.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.usuario.entity.Usuario;

@Stateless
public class UsuarioServiceBean implements Serializable {

	private static final long serialVersionUID = -8168826997906780211L;

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Integer PERFIL_TODOS = 0;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceBean.class);
	public static final String ERRO_INESPERADO = "display.ocorreu.erro.inesperado.favor.consultar.administrador";
	
	public void cadastrarUsuario(Usuario usuario) throws SbcuniException, Exception {
		if (null == usuario.getMatricula()) {
			throw new SbcuniException("display.campos.obrigatorios");
		}
		if (null != consultarPorCpf(usuario.getCpf(), usuario.getPerfil())) {
			throw new SbcuniException("display.ja.existe.usuario.cadastrado.cpf");
		}
		if (null != consultarPorEmail(usuario.getEmail(), usuario.getPerfil())) {
			throw new SbcuniException("display.ja.existe.usuario.cadastrado.email");
		}
		if (null != consultarPorMatricula(usuario.getMatricula(), usuario.getPerfil())) {
			throw new SbcuniException("display.ja.existe.usuario.cadastrado.matricula");
		}
		try {
			usuario.setDtCadastro(new Date());
			entityManager.persist(usuario);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new SbcuniException(ERRO_INESPERADO, e);
		}
	}
	
	public void alterarUsuario(Usuario usuario) throws SbcuniException {
		try {
			entityManager.merge(usuario);
		} catch (Exception e) {
			throw new SbcuniException("Erro ao alterar usuario", e);
		}
	}
	
	public void excluirUsuario(Usuario usuario) throws SbcuniException {
		try {
			usuario = entityManager.find(Usuario.class, usuario.getIdUsuario());
			entityManager.remove(usuario);
		} catch (Exception e) {
			throw new SbcuniException("Erro ao excluir usuário", e);
		}
	}
	
	public Usuario consultarPorCpf(String cpf, Integer perfil) throws SbcuniException {
		StringBuffer q = new StringBuffer("SELECT u FROM Usuario u WHERE u.cpf =:cpf");
		if (!perfil.equals(PERFIL_TODOS)) {
			q.append(" and u.perfil =:perfil");
		}
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("cpf", cpf);
		if (!perfil.equals(PERFIL_TODOS)) {
			query.setParameter("perfil", perfil);
		}
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario consultarPorEmail(String email, Integer perfil) throws SbcuniException {
		StringBuffer q = new StringBuffer("SELECT u FROM Usuario u WHERE u.email =:email");
		if (!perfil.equals(PERFIL_TODOS)) {
			q.append(" and u.perfil =:perfil");
		}
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("email", email);
		if (!perfil.equals(PERFIL_TODOS)) {
			query.setParameter("perfil", perfil);
		}
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> consultarPorNome(String nome, Integer perfil) throws SbcuniException {
		StringBuffer q = new StringBuffer("SELECT u FROM Usuario u WHERE lower(u.nome) like :nome");
		if (!perfil.equals(PERFIL_TODOS)) {
			q.append(" and u.perfil =:perfil");
		}
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("nome", nome.toLowerCase() + "%");
		if (!perfil.equals(PERFIL_TODOS)) {
			query.setParameter("perfil", perfil);
		}
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	public Usuario consultarPorMatricula(String matricula, Integer perfil) {
		StringBuffer q = new StringBuffer("SELECT u FROM Usuario u WHERE u.matricula = :matricula");
		if (!perfil.equals(PERFIL_TODOS)) {
			q.append(" and u.perfil =:perfil");
		}
		Query query = entityManager.createQuery(q.toString());
		query.setParameter("matricula", matricula);
		if (!perfil.equals(PERFIL_TODOS)) {
			query.setParameter("perfil", perfil);
		}
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> consultarPorPerfil(Integer perfil) {
		Query query = entityManager.createNamedQuery("Usuario.buscarPorPerfil");
		query.setParameter("perfil", perfil);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodos() {
		Query query = entityManager.createNamedQuery("Usuario.buscarTodos");
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario consultarUsuarioLogin(String matricula, String senha) throws SbcuniException {
		Query query = entityManager.createNamedQuery("Usuario.consultarUsuarioLogin");
		query.setParameter("matricula", matricula);
		query.setParameter("senha", senha);
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> consultarAlunoNomeOuMatricula(String nome, String matricula) {
		Query query = entityManager.createNamedQuery("Usuario.consultarAlunoNomeOuMatricula");
		query.setParameter("nome", nome.concat("%").toLowerCase());
		query.setParameter("matricula", matricula.concat("%"));
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> consultarAlunoNomeOuEmail(String nome, String email) {
		Query query = entityManager.createNamedQuery("Usuario.consultarAlunoNomeOuEmail");
		query.setParameter("nome", nome.concat("%").toLowerCase());
		query.setParameter("email", email.concat("%"));
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario consultarUsuarioPorId(Long idUsuario) {
		try {
			return entityManager.find(Usuario.class, idUsuario);
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Usuario> pesquisa(String consulta) {
		Query query = entityManager.createNamedQuery("Usuario.pesquisa");
		query.setParameter("nome", "%" + consulta.toLowerCase() + "%");
		query.setParameter("email", "%" + consulta.toLowerCase() + "%");
		query.setParameter("matricula", "%" + consulta.toLowerCase() + "%");
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	public Usuario consultarPorMatriculaCpf(String cpf, String matricula) {
		Query query = entityManager.createNamedQuery("Usuario.consultarPorMatriculaCpf");
		query.setParameter("cpf", cpf);
		query.setParameter("matricula", matricula);
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}
