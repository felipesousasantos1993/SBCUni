package br.com.sbcuni.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.sbcuni.categoria.entity.Categoria;

@FacesConverter(value = "categoriaConverter")
public class CategoriaConverter implements Converter {

	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(Long.valueOf(value));
		return categoria;
	}

	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
		if (object != null) {
			return String.valueOf(((Categoria) object).getIdCategoria());
		} else {
			return null;
		}
	}

}
