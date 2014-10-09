package br.com.sbcuni.pesquisa.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.categoria.entity.Categoria;
import br.com.sbcuni.categoria.service.CategoriaServiceBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.grupoEstudo.service.GrupoEstudoSerivceBean;
import br.com.sbcuni.mensagem.entity.Mensagem;
import br.com.sbcuni.mensagem.service.MensagemServiceBean;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.topico.service.TopicoServiceBean;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.usuario.service.UsuarioServiceBean;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class PesquisaBean extends GenericBean {

	private static final long serialVersionUID = 6539396363438480909L;

	public PesquisaBean() {
		super();
	}

	@EJB 
	private TopicoServiceBean topicoServiceBean;
	@EJB
	private GrupoEstudoSerivceBean grupoEstudoSerivceBean;
	@EJB
	private MensagemServiceBean mensagemServiceBean;
	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	@EJB
	private CategoriaServiceBean categoriaServiceBean;
	
	private List<Topico> topicos;
	private List<Mensagem> mensagens;
	private List<GrupoEstudo> grupoEstudos;
	private List<Usuario> usuarios;
	private List<Categoria> categorias;
	
	private String consulta;
	private Integer nuResultados = 0;
	
	public String pesquisar() {
		topicos = pesquisarTopicos();
		usuarios = pesquisarUsuarios();
		mensagens = pesquisarMensagens();
		grupoEstudos = pesquisarGrupos();
		categorias = pesquisarCategorias();
		
		nuResultados += topicos.size();
		nuResultados += usuarios.size();
		nuResultados += mensagens.size();
		nuResultados += grupoEstudos.size();
		nuResultados += categorias.size();
		WebResources.getFlash().put(WebResources.LISTA_TOPICOS, topicos);
		WebResources.getFlash().put(WebResources.LISTA_USUARIOS, usuarios);
		WebResources.getFlash().put(WebResources.LISTA_GRUPOS_ESTUDO, grupoEstudos);
		WebResources.getFlash().put(WebResources.LISTA_MENSAGENS, mensagens);
		WebResources.getFlash().put(WebResources.LISTA_CATEGORIAS, categorias);
		WebResources.getFlash().put(WebResources.PESQUISA, consulta);
		WebResources.getFlash().put(WebResources.NU_RESULTADOS_PESQUISA, nuResultados);
		return Tela.RESULTADO_PESQUISA_PATH;
	}
	
	public List<Topico> pesquisarTopicos() {
		return topicoServiceBean.buscarTopicosTituloDescricao(consulta, consulta);
	}
	
	public List<Usuario> pesquisarUsuarios() {
		return usuarioServiceBean.pesquisa(consulta);
	}
	public List<GrupoEstudo> pesquisarGrupos() {
		return grupoEstudoSerivceBean.pesquisa(consulta);
	}
	public List<Mensagem> pesquisarMensagens() {
		return mensagemServiceBean.pesquisa(consulta, UsuarioSessionBean.getInstance().getUsuarioSessao());
	}
	
	public List<Categoria> pesquisarCategorias() {
		return categoriaServiceBean.pesquisa(consulta);
	}

	public List<Topico> getTopicos() {
		return topicos;
	}

	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public List<GrupoEstudo> getGrupoEstudos() {
		return grupoEstudos;
	}

	public void setGrupoEstudos(List<GrupoEstudo> grupoEstudos) {
		this.grupoEstudos = grupoEstudos;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
}

