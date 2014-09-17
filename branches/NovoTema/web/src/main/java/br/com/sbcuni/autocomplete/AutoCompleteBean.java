package br.com.sbcuni.autocomplete;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import br.com.sbcuni.categoria.entity.Categoria;
import br.com.sbcuni.topico.service.TopicoServiceBean;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.usuario.service.UsuarioServiceBean;

@ManagedBean
public class AutoCompleteBean {

	@EJB
	private TopicoServiceBean topicoServiceBean;
	@EJB
	private UsuarioServiceBean usuarioServiceBean;

	public List<Categoria> completeCategoria(String query) {
		List<Categoria> allCategorias = topicoServiceBean.listarTodasCategorias();
		List<Categoria> filteredCategorias = new ArrayList<Categoria>();
		
		for (int i = 0; i < allCategorias.size(); i++) {
			Categoria skin = allCategorias.get(i);
			if (skin.getDeCategoria().toLowerCase().startsWith(query)) {
				filteredCategorias.add(skin);
			}
		}

		return filteredCategorias;
	}
	
	public List<Usuario> completarUsuario(String query) {
		return usuarioServiceBean.consultarAlunoNomeOuMatricula(query, query);
	}

}
