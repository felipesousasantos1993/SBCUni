package br.com.sbcuni.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sbcuni.usuario.entity.Usuario;

@FacesConverter(value = "alunosConverter")
public class AlunosConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(Long.valueOf(value));
		return usuario;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Usuario) object).getIdUsuario());
		} else {
			return null;
		}
	}

}
