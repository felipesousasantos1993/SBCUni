package br.com.sbcuni.constantes;

import java.io.Serializable;

public class Constantes implements Serializable {

	private static final long serialVersionUID = -7257485382485688002L;

	public static final String MENSAGEM = "mensagem";
	
	public static final Integer PERFIL_TODOS = 0;
	public static final Integer PERFIL_ALUNO = 1;
	public static final Integer PERFIL_PROFESSOR = 2;
	public static final Integer PERFIL_COODERNADOR = 3;
	
	public static final int FILTRO_NOME = 1;
	public static final int FILTRO_EMAIL = 2;
	public static final int FILTRO_MATRICULA = 3;
	public static final int FILTRO_CPF = 4;
	public static final int FILTRO_PERFIL = 5;
	public static final int FILTRO_TODOS = 6;
	
	public static final Boolean AVALIACAO_POSITIVA = Boolean.TRUE;
	public static final Boolean AVALIACAO_NEGATIVA = Boolean.FALSE;
}