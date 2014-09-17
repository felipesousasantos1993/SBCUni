package br.com.sbcuni.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Util {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);
	private static final String NOVE = "99999999999999";
	private static final String OITO = "88888888888888";
	private static final String SETE = "77777777777777";
	private static final String SEIS = "66666666666666";
	private static final String CINCO = "55555555555555";
	private static final String QUATRO = "44444444444444";
	private static final String TRES = "33333333333333";
	private static final String DOIS = "22222222222222";
	private static final String UM = "11111111111111";
	private static final String ZERO = "00000000000000";
	public static final Integer TAMANHOCPF=11;
	public static final String FORMATO_DDMMAAAA = "dd.MM.yyyy";
	public static final String FORMATO_DDMMAAAA_HHMM = "dd/MM/yyyy - HH:mm:ss";
	public static final String FORMATO_DDMMAAAA_COM_BARRA = "dd/MM/yyyy";


	public static String gerarMatricula(Integer perfil) {
		StringBuffer matricula = new StringBuffer("");
		Calendar dtAtual = Calendar.getInstance();
		matricula.append(String.valueOf(dtAtual.get(Calendar.YEAR)));
		matricula.append(String.valueOf(dtAtual.get(Calendar.MONTH)));
		matricula.append(String.valueOf(dtAtual.get(Calendar.SECOND)));
		matricula.append(String.valueOf(dtAtual.get(Calendar.MILLISECOND)));
		matricula.append(perfil.toString());
		
		return matricula.toString();
	}

	public static Boolean isBlankOrNull(final String value) {
		return null == value || "".equals(value.trim());
	}
	
	public static Boolean isNull(Object object){
		return null == object;
	}
	public static boolean isNumeric(String str){
		if (isBlankOrNull(str)) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	public static String removerZerosEsquerda(String str) {
		if (!isNumeric(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while(sb.length() > 0 && sb.charAt(0) == '0') {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	public static String retiraMascara(String str) {
		String strLocal = str;
		if (!isBlankOrNull(strLocal)) {
			strLocal = strLocal.replaceAll("\\.|-|/", "");
		}
		return strLocal;
	}

	public static String formatarCPFCNPJ(String string) {
		if (isBlankOrNull(string)) {
			return "";
		}
		return new StringBuffer(formatarCpf(string)).toString();
	}

	public static Date parseDate(String str) throws ParseException {
		return Util.parseDate(FORMATO_DDMMAAAA_COM_BARRA, str);
	}

	public static Date parseDate(String formato, String str) throws ParseException {
		if (isBlankOrNull(str)) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(formato);
		sdf.setLenient(Boolean.FALSE);
		return sdf.parse(str);
	}

	public static String formatDateHora(Date data) {
		if (isNull(data)) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DDMMAAAA_HHMM);
		return sdf.format(data);
	}

	public static String formatDate(Date data) {
		if (isNull(data)) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_DDMMAAAA_COM_BARRA);
		return sdf.format(data);
	}
	
	public static String formatarCpf(String vCpf){
		String retorno = "";
		String cpf = vCpf;
		int zero = 0;
		if(cpf != null && cpf.length() > zero){  
			while(cpf.length() < TAMANHOCPF){
				cpf = "0".concat(cpf);
			}

			String primeiraParte = cpf.substring(0, 3);
			String segundaParte  = cpf.substring(3, 6);
			String terceiraParte = cpf.substring(6, 9);
			String dv            = cpf.substring(9, cpf.length());

			retorno =  primeiraParte +"."+segundaParte+"."+terceiraParte+"-"+dv;
		}

		return retorno;
	}
	/**.
     * Objetivo: metodo responsavel por validar um CPF
     * @param VcPF
     * @return boolean
     * @author c112460
     * @since 06/08/2013
     * @version 1.0
     */	
	public static boolean validaCPF(String vCpf) {
		
		if (vCpf == null){
			return false;
		}

		String cpf = vCpf.replaceAll("\\.", "").replace("-", "");
		
	    if (cpf.equals(ZERO.substring(0, TAMANHOCPF)) || cpf.equals(UM.substring(0, TAMANHOCPF)) 
	    		|| cpf.equals(DOIS.substring(0, TAMANHOCPF)) || cpf.equals(TRES.substring(0, TAMANHOCPF)) 
	    		|| cpf.equals(QUATRO.substring(0, TAMANHOCPF)) || cpf.equals(CINCO.substring(0, TAMANHOCPF)) 
	    		|| cpf.equals(SEIS.substring(0, TAMANHOCPF)) || cpf.equals(SETE.substring(0, TAMANHOCPF)) 
	    		|| cpf.equals(OITO.substring(0, TAMANHOCPF)) || cpf.equals(NOVE.substring(0, TAMANHOCPF)) 
	    		|| (cpf.length() != TAMANHOCPF)){
	        return false;
	    }
	 
	    char dig10, dig11;
	    int soma, indice, resto, num, peso;
	 
	    try{
	      soma = 0;
	      peso = 10;
	      for (indice=0; indice<9; indice++){             
	        num = (int)(cpf.charAt(indice) - 48);
	        soma = soma + (num * peso);
	        peso = peso - 1;
	      }
	      resto = 11 - (soma % 11);
	      if ((resto == 10) || (resto == 11)){
	         dig10 = '0';
	      } else{
	    	  dig10 = (char)(resto + 48);
	      }
	      soma = 00;
	      peso = 11;
	      for(indice=00; indice<10; indice++){
	        num = (int)(cpf.charAt(indice) - 48);
	        soma = soma + (num * peso);
	        peso = peso - 01;
	      }
	      resto = 11 - (soma % 11);
	      if ((resto == 10) || (resto == 11)){
	         dig11 = '0';
	      } else{
	    	  dig11 = (char)(resto + 48);
	      }
	      
	      return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));

	    } catch (InputMismatchException erro) {
	    	LOGGER.error(erro.getMessage());
	    }
	    return false;
	}
	  

}
