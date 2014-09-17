package br.com.sbcuni.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import br.com.sbcuni.util.Util;

@FacesValidator(value = "consultarUsuarioValidator")
public class ConsultarUsuarioValidator extends GenericValidator {

	private String tpFiltro;
	private String parametro;
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		initializeForm(component);
		validaTpFiltro();
		defineParametro(component);
		validaParametro();
	}

	@Override
	protected void initializeForm(UIComponent component) {
		tpFiltro = getValorCombo(component, "tpFiltro");
	}
	
	private void defineParametro(UIComponent component) {
		if ("1".equals(tpFiltro)) {
			parametro = getValorTexto(component, "nomeInput");
		} else if ("2".equals(tpFiltro)) {
			parametro = getValorTexto(component, "emailInput");
		} else if ("3".equals(tpFiltro)) {
			parametro = getValorTexto(component, "matriculaInput");
		} else if ("4".equals(tpFiltro)) {
			parametro = getValorTexto(component, "cpfInput");
		} else if ("5".equals(tpFiltro)) {
			parametro = getValorCombo(component, "perfilSelect");
		}
	}

	private void validaTpFiltro() {
		if (Util.isBlankOrNull(tpFiltro)) {
			exibirMsgErroValidacao("display.selecione.tipo.filtro", "consultar");
		}
	}
	
	private void validaParametro() {
		if (Util.isBlankOrNull(parametro) && !"6".equals(tpFiltro)) {
			exibirMsgErroValidacao("display.informe.parametro.consulta", "consultar");
		}
	}
	
}
