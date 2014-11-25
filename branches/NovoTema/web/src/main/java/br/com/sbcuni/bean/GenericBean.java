package br.com.sbcuni.bean;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.com.sbcuni.avaliacao.entity.Avaliacao;
import br.com.sbcuni.avaliacao.service.AvaliacaoServiceBean;
import br.com.sbcuni.categoria.entity.Categoria;
import br.com.sbcuni.comentario.entity.Comentario;
import br.com.sbcuni.constantes.Constantes;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.mensagem.entity.Mensagem;
import br.com.sbcuni.relatorios.ConstantesRelatorio;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.topico.service.TopicoServiceBean;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.Util;
import br.com.sbcuni.util.WebResources;

public class GenericBean implements Serializable {

	private static final long serialVersionUID = -186630473546952482L;

	private String infos;
	private transient ResourceBundle messages;
	private String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@EJB
	private AvaliacaoServiceBean avaliacaoServiceBean;
	@EJB
	private TopicoServiceBean topicoServiceBean;
	
	public static String getMensagem(String key, Object... args) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		ResourceBundle mensagens = ctx.getApplication().getResourceBundle(ctx, "mensagens");
		String msg = mensagens.getString(key);
		return MessageFormat.format(msg, args);
	}

	protected void exibirMsgSucesso(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
	}

	protected void exibirMsgAviso(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
	}

	protected void exibirMsgErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, mensagem, null));
	}

	protected void exibirMsgInfo(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, mensagem, null));
	}

	public String detalharTopico(Topico topico) {
		WebResources.getFlash().put(WebResources.TOPICO, topico);
		topico.setNuVisualizacoes(topico.getNuVisualizacoes()+1);
		topicoServiceBean.alterarTopico(topico);
		return Tela.VISUALIZAR_TOPICO_PATH;
	}
	public String detalharGrupoEstudo(GrupoEstudo grupoEstudo) {
		WebResources.getFlash().put(WebResources.GRUPO_ESTUDO, grupoEstudo);
		grupoEstudo.setTopicosGrupo(topicoServiceBean.buscarTopicosGrupo(grupoEstudo));
		return Tela.DETALHE_GRUPO_ESTUDO_PATH;
	}

	public static String telaAtualizarTopico(Topico topico) {
		WebResources.getFlash().put(WebResources.TOPICO, topico);
		return Tela.ATUALIZAR_TOPICO_PATH;
	}
	
	public String verMensagem(Mensagem msg) {
		WebResources.getFlash().put(WebResources.MENSAGEM, msg);
		WebResources.getFlash().put(WebResources.TELA, "verMensagem");
		return Tela.CAIXA_ENTRADA;
	}
	
	public String enviarMensagemPrivada(Usuario usuario) {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		WebResources.getFlash().put(WebResources.TELA, "enviarMensagem");
		return Tela.CAIXA_ENTRADA;
	}
	
	public String buscarTopicosCategoria(Categoria categoria) {
		WebResources.getFlash().put(WebResources.CATEGORIA, categoria);
		WebResources.getFlash().put(WebResources.LISTA_TOPICOS, topicoServiceBean.buscarTopicoPorCategoria(categoria));
		return Tela.TOPICOS_CATEGORIAS;
	}

	public void curtirTopico(Topico topico) {
		Avaliacao avaliacao = new Avaliacao();
		if (Util.isNull(topico.getAvaliacaoUsuario())) {
			avaliacao.setAvaliacao(Constantes.AVALIACAO_POSITIVA);
			avaliacao.setUsuario(UsuarioSessionBean.getInstance().getUsuarioSessao());
			List<Topico> lista = new ArrayList<Topico>();
			lista.add(topico);
			avaliacao.setTopicos(new ArrayList<Topico>(lista));
			avaliacao.setComentarios(new ArrayList<Comentario>());
		} else {
			topico.getAvaliacaoUsuario().setAvaliacao(Constantes.AVALIACAO_POSITIVA);
			avaliacao = topico.getAvaliacaoUsuario();
		}
		try {
			avaliacaoServiceBean.avaliar(avaliacao);
			avaliacaoServiceBean.definirAvaliacaoTopico(topico);
			topico.setAvaliacaoUsuario(avaliacaoServiceBean.verificarAvaliacaoUsuarioTopico(UsuarioSessionBean.getInstance().getUsuarioSessao(), topico));
		} catch (SbcuniException e) {
			exibirMsgErro(getMensagem("display.erro.avaliar.topico", WebResources.MENSAGEM));
		}
	}

	public void descurtirTopico(Topico topico) {
		Avaliacao avaliacao = new Avaliacao();
		if (Util.isNull(topico.getAvaliacaoUsuario())) {
			avaliacao.setAvaliacao(Constantes.AVALIACAO_NEGATIVA);
			avaliacao.setUsuario(UsuarioSessionBean.getInstance().getUsuarioSessao());
			List<Topico> lista = new ArrayList<Topico>();
			lista.add(topico);
			avaliacao.setTopicos(new ArrayList<Topico>(lista));
			avaliacao.setComentarios(new ArrayList<Comentario>());
		} else {
			topico.getAvaliacaoUsuario().setAvaliacao(Constantes.AVALIACAO_NEGATIVA);
			avaliacao = topico.getAvaliacaoUsuario();
		}
		try {
			avaliacaoServiceBean.avaliar(avaliacao);
			avaliacaoServiceBean.definirAvaliacaoTopico(topico);
			topico.setAvaliacaoUsuario(avaliacaoServiceBean.verificarAvaliacaoUsuarioTopico(UsuarioSessionBean.getInstance().getUsuarioSessao(), topico));
		} catch (SbcuniException e) {
			exibirMsgErro(getMensagem("display.erro.avaliar.topico", WebResources.MENSAGEM));
		}
	}

	public void curtirComentario(Comentario comentario) {
		Avaliacao avaliacao = new Avaliacao();
		if (Util.isNull(comentario.getAvaliacaoUsuario())) {
			avaliacao.setAvaliacao(Constantes.AVALIACAO_POSITIVA);
			avaliacao.setUsuario(UsuarioSessionBean.getInstance().getUsuarioSessao());
			List<Comentario> lista = new ArrayList<Comentario>();
			lista.add(comentario);
			avaliacao.setComentarios(new ArrayList<Comentario>(lista));
			avaliacao.setTopicos(new ArrayList<Topico>());
		} else {
			comentario.getAvaliacaoUsuario().setAvaliacao(Constantes.AVALIACAO_POSITIVA);
			avaliacao = comentario.getAvaliacaoUsuario();
		}
		try {
			avaliacaoServiceBean.avaliar(avaliacao);
			avaliacaoServiceBean.definirAvaliacaoComentario(comentario);
			comentario.setAvaliacaoUsuario(avaliacaoServiceBean.verificarAvaliacaoUsuarioComentario(UsuarioSessionBean.getInstance().getUsuarioSessao(), comentario));
		} catch (SbcuniException e) {
			exibirMsgErro(getMensagem("display.erro.avaliar.comentario", WebResources.MENSAGEM));
		}
	}

	public void descurtirComentario(Comentario comentario) {
		Avaliacao avaliacao = new Avaliacao();
		if (Util.isNull(comentario.getAvaliacaoUsuario())) {
			avaliacao.setAvaliacao(Constantes.AVALIACAO_NEGATIVA);
			avaliacao.setUsuario(UsuarioSessionBean.getInstance().getUsuarioSessao());
			List<Comentario> lista = new ArrayList<Comentario>();
			lista.add(comentario);
			avaliacao.setComentarios(new ArrayList<Comentario>(lista));
			avaliacao.setTopicos(new ArrayList<Topico>());
		} else {
			comentario.getAvaliacaoUsuario().setAvaliacao(Constantes.AVALIACAO_NEGATIVA);
			avaliacao = comentario.getAvaliacaoUsuario();
		}
		try {
			avaliacaoServiceBean.avaliar(avaliacao);
			avaliacaoServiceBean.definirAvaliacaoComentario(comentario);
			comentario.setAvaliacaoUsuario(avaliacaoServiceBean.verificarAvaliacaoUsuarioComentario(UsuarioSessionBean.getInstance().getUsuarioSessao(), comentario));
		} catch (SbcuniException e) {
			exibirMsgErro(getMensagem("display.erro.avaliar.comentario", WebResources.MENSAGEM));
		}
	}

	public void removerAvaliacaoComentario(Comentario comentario) {
		avaliacaoServiceBean.removerAvaliacao(comentario.getAvaliacaoUsuario());
		avaliacaoServiceBean.definirAvaliacaoComentario(comentario);
		comentario.setAvaliacaoUsuario(avaliacaoServiceBean.verificarAvaliacaoUsuarioComentario(UsuarioSessionBean.getInstance().getUsuarioSessao(), comentario));
	}

	public void removerAvaliacaoTopico(Topico topico) {
		avaliacaoServiceBean.removerAvaliacao(topico.getAvaliacaoUsuario());
		avaliacaoServiceBean.definirAvaliacaoTopico(topico);
		topico.setAvaliacaoUsuario(avaliacaoServiceBean.verificarAvaliacaoUsuarioTopico(UsuarioSessionBean.getInstance().getUsuarioSessao(), topico));
	}

	public static String breadcrumbRelatorio(String entrada) {

		String[] cortada = entrada.split("/");
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < cortada.length; i++) {

			buffer.append(getMensagem(cortada[i]));
			buffer.append('-');

		}
		String saida = buffer.toString();
		return saida.substring(0, saida.length() - 1).replace("-", " > ");
	}
	 
	public  List<SelectItem> getEstados() {
		List<SelectItem> estados = new ArrayList<SelectItem>();
		estados.add(new SelectItem("RJ", "RJ"));
		estados.add(new SelectItem("SP", "SP"));
		return estados;
	}

	public String perfilUsuario(Usuario usuario) {
		WebResources.getFlash().put(WebResources.USUARIO, usuario);
		return Tela.PEFIL_PATH;
	}
	
	public static Map<String, Object> adicionaImagemCabecalho(Map<String, Object> entrada, FacesContext context) {
		entrada.put("logo",context.getExternalContext().getRealPath(ConstantesRelatorio.LOGO));
		return entrada;
	}
	
	protected ResourceBundle getMessages() {
		if (messages == null) {
			messages = ResourceBundle.getBundle("br.com.sbcuni.mensagens.mensagens");
		}
		return messages;
	}

	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}

	public String getEmailPattern() {
		return emailPattern;
	}

	public void setEmailPattern(String emailPattern) {
		this.emailPattern = emailPattern;
	}

}