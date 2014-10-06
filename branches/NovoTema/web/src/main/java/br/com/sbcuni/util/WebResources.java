package br.com.sbcuni.util;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class WebResources {
	
	public static final String MENSAGEM = "mensagem";
	public static final String TOPICO = "topico";
	public static final String USUARIO = "usuario";
	public static final String PERFIL = "perfil";
	public static final String LISTA_USUARIOS = "listaUsuarios";
	public static final String GRUPO_ESTUDO = "grupoEstudo";
	public static final String LISTA_TOPICOS = "listaTopicos";
	public static final String PESQUISA = "pesquisa";
	public static final String NU_RESULTADOS_PESQUISA = "nuResultadosPesquisa";
	public static final String LISTA_GRUPOS_ESTUDO = "listaGruposEstudo";
	public static final String LISTA_MENSAGENS = "listaMensagens";
	
	public static final String ERRO_INESPERADO = "display.ocorreu.erro.inesperado.favor.consultar.administrador";
	
	public static final String CONTEXT_PATH = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
	
	public static Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	
}
