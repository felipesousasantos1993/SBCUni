package br.com.sbcuni.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sbcuni.util.Util;

@FacesConverter(value = "cpfConverter")
public class CpfConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return Util.formatarCpf(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return Util.formatarCpf(value.toString());
	}

}
