package br.com.sbcuni.categoria.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import br.com.sbcuni.topico.entity.Topico;

@Entity
@NamedQueries({
	@NamedQuery(name = "Categoria.listarTodasCategorias", query = "SELECT c FROM Categoria c"),
	@NamedQuery(name = "Categoria.buscarCategoriaPorId", query = "SELECT c FROM Categoria c WHERE c.idCategoria =:idCategoria")
})
public class Categoria implements Serializable {

	private static final long serialVersionUID = 6498231603632703636L;

	@Id
	@GeneratedValue
	@Column(name = "idCategoria", length = 5, nullable = false)
	private Long idCategoria;

	@Column(name = "deCategoria", length = 20, nullable = false)
	private String deCategoria;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "categorias", targetEntity = Topico.class)
	private List<Topico> topicos;
	

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}


	public String getDeCategoria() {
		return deCategoria;
	}

	public void setDeCategoria(String deCategoria) {
		this.deCategoria = deCategoria;
	}

	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

}
