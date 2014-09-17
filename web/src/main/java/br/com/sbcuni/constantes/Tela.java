package br.com.sbcuni.constantes;

import java.io.Serializable;

public class Tela implements Serializable {

	private static final long serialVersionUID = -1230412332202812219L;
	
	public static final String LOGIN = "/principal/login.jsf";
	
	// TOPICO
	public static final String NOVO_TOPICO = "novoTopico";
	public static final String TESTE = "teste";
	public static final String ATUALIZAR_TOPICO = "atualizarTopico";
	public static final String VISUALIZAR_TOPICO = "visualizarTopico";
	public static final String ATUALIZAR_TOPICO_PATH = "/paginas/topico/atualizarTopico.jsf";
	public static final String VISUALIZAR_TOPICO_PATH = "/paginas/topico/visualizarTopico.jsf";
	public static final String MEUS_TOPICOS = "meusTopicos";
	public static final String MEUS_TOPICOS_LOGIN = "/paginas/topico/meusTopicos.jsf";
	public static final String ULTIMOS_TOPICOS_LOGIN = "/paginas/topico/ultimosTopicos.jsf";
	
	// USUARIO
	public static final String DETALHAR_USUARIO = "detalharUsuario";
	public static final String CADASTRAR_USUARIO = "cadastrarUsuario";
	public static final String CONSULTAR_USUARIO = "consultarUsuario";
	public static final String ALTERAR_USUARIO = "alterarUsuario";
	public static final String EXCLUIR_USUARIO = "excluirUsuario";
	public static final String LISTAR_USUARIO = "listarUsuario";
	
	public static final String DETALHAR = "detalhar";
	public static final String LISTA = "lista";
	public static final String ALTERAR = "alterar";
	public static final String CONSULTAR = "consultar";
	public static final String EXCLUIR = "excluir";
	
	public static final String CADASTRAR_USUARIO_MENU = "/paginas/usuario/cadastrarUsuario.jsf";
	public static final String DETALHAR_USUARIO_MENU = "/paginas/usuario/detalharUsuario.jsf";
	public static final String CONSULTAR_ALTERAR_USUARIO_MENU = "/paginas/usuario/consultarAlterarUsuario.jsf";
	public static final String CONSULTAR_USUARIO_MENU = "/paginas/usuario/consultarUsuario.jsf";
	public static final String CONSULTAR_EXCLUIR_USUARIO_MENU = "/paginas/usuario/consultarExcluirUsuario.jsf";

	// GRUPO DE ESTUDO
	public static final String MEUS_GRUPOS = "meusGrupos";
	public static final String DETALHE_GRUPO_ESTUDO = "detalheGrupoEstudo";
	public static final String NOVO_TOPICO_GRUPO_ESTUDO = "novoTopicoGrupoEstudo";
}
