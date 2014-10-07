package br.com.sbcuni.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.Util;

public class SegurancaFilter implements Filter {

	private static final Integer PERFIL_ALUNO = 1;
	private static final Integer PERFIL_PROFESSOR = 2;
	private static final Integer PERFIL_COORDENADOR = 3;
	private static Usuario usuario;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		usuario = (Usuario) session.getAttribute("usuario");
		HttpServletResponse resp = (HttpServletResponse) response;

		if (paginasLiberadas(req) || !Util.isNull(usuario)) {
			Integer perfil = recuperaPerfilFuncionario(session);
			if (!Util.isNull(perfil)) {
				if (PERFIL_COORDENADOR.equals(perfil)) {
					if (isUrlRestritaAoCoordenador(req)) {
						resp.sendRedirect(req.getContextPath().concat(Tela.SEM_PERMISSAO));
					} else {
						if (isPaginasLoginPrimeiroLogado(req)) {
							resp.sendRedirect(req.getContextPath().concat(Tela.ULTIMOS_TOPICOS_LOGIN));
						} else {
							chain.doFilter(request, response);
						}
					}
				} else if (PERFIL_PROFESSOR.equals(perfil)) {
					if (isUrlRestritaAoProfessor(req)) {
						resp.sendRedirect(req.getContextPath().concat(Tela.SEM_PERMISSAO));
					} else {
						if (isPaginasLoginPrimeiroLogado(req)) {
							resp.sendRedirect(req.getContextPath().concat(Tela.ULTIMOS_TOPICOS_LOGIN));
						} else {
							chain.doFilter(request, response);
						}
					}
				} else if (PERFIL_ALUNO.equals(perfil)) {
					if (isUrlRestritaAoAluno(req)) {
						resp.sendRedirect(req.getContextPath().concat(Tela.SEM_PERMISSAO));
					} else {
						if (isPaginasLoginPrimeiroLogado(req)) {
							resp.sendRedirect(req.getContextPath().concat(Tela.ULTIMOS_TOPICOS_LOGIN));
						} else {
							chain.doFilter(request, response);
						}
					}
				} else if (perfil.equals(4)) {
					chain.doFilter(request, response);
				}
			} else {
				chain.doFilter(request, response);
			}
		} else {
			resp.sendRedirect(req.getContextPath().concat(Tela.LOGIN));
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	private Integer recuperaPerfilFuncionario(HttpSession session) {
		usuario = (Usuario) session.getAttribute("usuario");
		if (usuario != null) {
			return usuario.getPerfil();
		}
		return null;
	}
	
	private Boolean isPaginasLoginPrimeiroLogado(HttpServletRequest req) {
		return (req.getRequestURI().endsWith("login.jsf") 
				|| req.getRequestURI().endsWith("primeiroAcesso.jsf"));
	}

	private Boolean paginasLiberadas(HttpServletRequest req) {
		return (req.getRequestURI().endsWith("login.jsf") 
				|| req.getRequestURI().contains("javax.faces.resource") 
				|| req.getRequestURI().contains("primeiroAcesso.jsf")
				|| req.getRequestURI().contains("template")
				|| req.getRequestURI().contains("resources"));
	}

	private Boolean isUrlRestritaAoCoordenador(HttpServletRequest request) {
		return (request.getRequestURI().contains("/gerencia/"));
	}

	private Boolean isUrlRestritaAoProfessor(HttpServletRequest request) {
		return (request.getRequestURI().contains("/gerencia/") 
				|| request.getRequestURI().contains("/categorias/"));
	}

	private Boolean isUrlRestritaAoAluno(HttpServletRequest request) {
		return (request.getRequestURI().contains("/gerencia/") 
				|| request.getRequestURI().contains("/categorias/") 
				|| request.getRequestURI().endsWith("/usuario/altearUsuario.jsf") 
				|| request.getRequestURI().endsWith("/usuario/cadastrarUsuario.jsf")
				|| request.getRequestURI().endsWith("/usuario/excluirUsuario.jsf") 
				|| request.getRequestURI().endsWith("/grupoEstudo/criarGrupo.jsf"));
	}

}
