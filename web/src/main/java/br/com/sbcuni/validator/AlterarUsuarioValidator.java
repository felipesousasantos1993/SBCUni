package br.com.sbcuni.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.ValidatorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.sbcuni.util.Util;

@FacesValidator(value = "alterarUsuarioValidator")
public class AlterarUsuarioValidator extends GenericValidator {
	
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private String confirmarSenha;
	private Boolean alterarSenha;

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioValidator.class);
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	@Override
	public void validate(FacesContext context, UIComponent component, Object object) throws ValidatorException {
		initializeForm(component);
		validaCamposObrigatorios();
		validarNome();
		validaCpf();
		validaEmail();
		if (alterarSenha) {
			validaSenha();
		}
	}
	
	public void validaCamposObrigatorios() {
		if (alterarSenha) {
			if (Util.isBlankOrNull(nome)
					|| Util.isBlankOrNull(cpf)
					|| Util.isBlankOrNull(email)
					|| Util.isBlankOrNull(senha)
					|| Util.isBlankOrNull(confirmarSenha)
					) {
				exibirMsgErroValidacao("display.campos.obrigatorios", "cadastrarAluno");
			}
		} else {
			if (Util.isBlankOrNull(nome)
					|| Util.isBlankOrNull(cpf)
					|| Util.isBlankOrNull(email)
					) {
				exibirMsgErroValidacao("display.campos.obrigatorios", "cadastrarAluno");
			}
		}
	}
	
	public void validarNome() {
		if (3 > nome.length()) {
			exibirMsgErroValidacao("display.nome.precisa.mais.2.digitos", "cadastrarAluno");
		}
	}
	public void validaCpf() {
		if (11 != Util.retiraMascara(cpf).length()) {
			exibirMsgErroValidacao("display.cpf.deve.11.digitos", "cadastrarAluno");
		}
	}
	
	public void validaSenha() {
		if(6 > senha.length() || 8 < senha.length()) {
			exibirMsgErroValidacao("display.senha.deve.entre.6.8.digitos", "cadastrarAluno");
		} else if (!senha.equals(confirmarSenha)) {
			exibirMsgErroValidacao("display.senhas.nao.conferem", "cadastrarAluno");
		}
	}
	
	public void validaEmail() {
		if (!email.matches(EMAIL_PATTERN)) {
			exibirMsgErroValidacao("display.email.invalido", "cadastrar");
		}
	}


	@Override
	protected void initializeForm(UIComponent component) {
		try {
			alterarSenha = Boolean.valueOf(getValorCombo(component, "alterarSenha"));
			nome = getValorTexto(component, "nome");
			cpf = getValorTexto(component, "cpf");
			email = getValorTexto(component, "email");
			senha = getValorTexto(component, "senha");
			confirmarSenha = getValorTexto(component, "confirmarSenha");
		} catch (Exception e) {
			LOGGER.error("Erro ao pegar dados da tela!");
		}
	}

}
