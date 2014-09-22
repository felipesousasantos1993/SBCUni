package br.com.sbcuni.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "mudarSenhaValidator")
public class MudarSenhaValidator extends GenericValidator {

	private String senhaNova;
	private String confirmaSenha;
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		initializeForm(component);
		if (!senhaNova.equals(confirmaSenha)) {
			exibirMsgErroValidacao("display.senhas.nao.conferem", "mudarSenha");
		}
	}

	@Override
	protected void initializeForm(UIComponent component)  {
		senhaNova = getValorTexto(component, "senhaNova");
		confirmaSenha = getValorTexto(component, "confirmaSenha");

	}

}
