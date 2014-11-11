package br.com.sbcuni.validator;

import java.text.ParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import br.com.sbcuni.util.Util;

@FacesValidator(value = "cpfValidator")
public class CpfValidator extends GenericValidator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		
		if (!Util.isNull(object) && !Util.validaCPF(object.toString())) {
			exibirMsgErroValidacao("display.cpf.invalido", null);
		}
		
	}

	@Override
	protected void initializeForm(UIComponent component) throws ParseException {
		
	}

	
	
}
