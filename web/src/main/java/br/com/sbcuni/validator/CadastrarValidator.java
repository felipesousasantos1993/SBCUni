package br.com.sbcuni.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import br.com.sbcuni.util.Util;

@FacesValidator(value = "cadastrarValidator")
public class CadastrarValidator extends GenericValidator {

	private String perfil;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		initializeForm(component);
		validaCamposObrigatorios();
		validarNome();
		validaCpf();
		validaSenha();
		validaEmail();
	}

	@Override
	protected void initializeForm(UIComponent component) {
		perfil = getValorCombo(component, "perfil");
		nome = getValorTexto(component, "nome");
		cpf = getValorTexto(component, "cpf");
		email = getValorTexto(component, "email");
		senha = getValorTexto(component, "senha");
	}
	
	public void validaCamposObrigatorios() {
		if (Util.isBlankOrNull(nome)
				|| Util.isBlankOrNull(cpf)
				|| Util.isBlankOrNull(email)
				|| Util.isBlankOrNull(senha)
				|| "0".equals(perfil)
				) {
			exibirMsgErroValidacao("display.campos.obrigatorios", "cadastrar");
		}
	}
	public void validarNome() {
		if (3 > nome.length()) {
			exibirMsgErroValidacao("display.nome.precisa.mais.2.digitos", "cadastrar");
		}
	}
	public void validaCpf() {
		if (11 != Util.retiraMascara(cpf).length()) {
			exibirMsgErroValidacao("display.cpf.deve.11.digitos", "cadastrar");
		}
	}
	public void validaSenha() {
		if(6 > senha.length() || 8 < senha.length()) {
			exibirMsgErroValidacao("display.senha.deve.entre.6.8.digitos", "cadastrar");
		}
	}
	public void validaEmail() {
		if (!email.matches(EMAIL_PATTERN)) {
			exibirMsgErroValidacao("display.email.invalido", "cadastrar");
		}
	}

}
