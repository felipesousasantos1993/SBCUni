package br.com.sbcuni.validator;

import java.text.ParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "cpfValidator")
public class CpfValidator extends GenericValidator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		
	}

	@Override
	protected void initializeForm(UIComponent component) throws ParseException {
		
	}

	
	
}
