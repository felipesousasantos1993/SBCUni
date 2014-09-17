package br.com.sbcuni.teste.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.topico.entity.Topico;

/**
 * Nome : TesteBean 
 * Objetivo: Classe de Teste
 * @since : Data de criação 02/05/2014 15:01:26
 * @author fesantos
 * @version $Revision: 1.0 $
 */
@ViewScoped
@ManagedBean
public class TesteBean extends GenericBean implements Serializable {

	private static final long serialVersionUID = 229460087527961322L;

	public SelectItem radio() {
		return new SelectItem("Radiio");
	}
	
	public void exibirMsg() {
		exibirMsgErro("Mensagem de Teste de erro");
		exibirMsgAviso("Mensagem de Teste de aviso");
		exibirMsgInfo("Mensagem de Teste de info");
	}
	
	public List<Topico> populaListaFake() {
		Topico topico;
		List<Topico> lista = new ArrayList<Topico>();

		for (int i = 0; i < 10; i++) {
			topico = new Topico();
			topico.setTitulo("Como usar JSF com Primefaces?");
			topico.setDescricao(i +" - Necessário usar o jboss 7.");
			topico.setDtCriacao(new Date());
			lista.add(topico);
		}

		return lista;

	}
	

}
