package br.com.sbcuni.categoria.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.categoria.entity.Categoria;
import br.com.sbcuni.categoria.service.CategoriaServiceBean;

@ManagedBean
@ViewScoped
public class NovaCategoriaBean extends GenericBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7281289381851176917L;
	
	@EJB
	private CategoriaServiceBean categoriaServiceBean;
	
	private Categoria categoria = new Categoria();
	
	
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
