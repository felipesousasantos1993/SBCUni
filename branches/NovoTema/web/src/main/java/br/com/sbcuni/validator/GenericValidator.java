package br.com.sbcuni.validator;

import java.text.ParseException;

import javax.faces.application.FacesMessage;
import javax.faces.component.ContextCallback;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Constantes;

/**
 * Nome : GenericValidator Objetivo: Classe que possui os métodos padrões de um
 * validator. Data de criação 21/01/2014 12:45:55
 * 
 * @author fesantos
 * @version $Revision: 1.0 $
 */
public abstract class GenericValidator implements Validator {

	protected abstract void initializeForm(UIComponent component) throws ParseException;

	protected String getValorTexto(UIComponent component, String campo) {
		UIInput otherInput = (UIInput) component.findComponent(campo);
		return otherInput != null && otherInput.getSubmittedValue() != null ? (String) otherInput.getSubmittedValue() : "";
	}

	protected String getValorCombo(UIComponent component, String campo) {
		UISelectOne ui = (UISelectOne) component.findComponent(campo);
		return ui != null && ui.getSubmittedValue() != null ? ui.getSubmittedValue().toString() : "";
	}

	protected String getValorTextoIdExato(UIComponent component, String idExato) {
		final StringBuilder value = new StringBuilder();

		FacesContext.getCurrentInstance().getViewRoot().invokeOnComponent(FacesContext.getCurrentInstance(), idExato, new ContextCallback() {
			public void invokeContextCallback(FacesContext context, UIComponent component) {
				UIInput ui = (UIInput) component;
				value.append(ui != null && ui.getSubmittedValue() != null ? ui.getSubmittedValue().toString() : "");
			}
		});
		return value.toString();
	}

	protected void exibirMsgErroValidacao(String msg, String tela) {
		throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, GenericBean.getMensagem(msg, Constantes.MENSAGEM), tela));
	}

}