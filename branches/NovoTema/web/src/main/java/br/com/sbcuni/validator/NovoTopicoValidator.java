package br.com.sbcuni.validator;

import java.text.ParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.util.Util;

@FacesValidator(value = "novoTopicoValidator") 
public class NovoTopicoValidator extends GenericValidator {

	private String titulo;
	private String descricao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NovoTopicoValidator.class);
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		try {
			initializeForm(component);
		} catch (ParseException e) {
			LOGGER.error("Erro ao pegar valores tópico");
		}
		validaCampos();
		
	}

	/**
	 * Método   : validaCampos
	 * Descrição: Verifica se algum campo está vazio.
	 */
	public void validaCampos() { 
		if (Util.isBlankOrNull(titulo)
				|| Util.isBlankOrNull(descricao)
				) {
			exibirMsgErroValidacao("display.campos.obrigatorios", Tela.NOVO_TOPICO);
		}
	}
	
	@Override
	protected void initializeForm(UIComponent component) throws ParseException {
		titulo = getValorTexto(component, "titulo");
		descricao = getValorTexto(component, "descricao");
	}

}
