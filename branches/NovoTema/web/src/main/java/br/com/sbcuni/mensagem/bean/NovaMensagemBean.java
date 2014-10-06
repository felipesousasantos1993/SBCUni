package br.com.sbcuni.mensagem.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Constantes;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.mensagem.entity.Mensagem;
import br.com.sbcuni.mensagem.service.MensagemServiceBean;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.usuario.service.UsuarioServiceBean;

@ViewScoped
@ManagedBean
public class NovaMensagemBean extends GenericBean {

	private static final long serialVersionUID = 8079788289970055879L;

	public NovaMensagemBean() {
		super();
	}

	private Mensagem mensagem = new Mensagem();
	private List<Usuario> destinatarios = new ArrayList<Usuario>();
	private List<String> destinatariosSelecionadas = new ArrayList<String>();

	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	@EJB
	private MensagemServiceBean mensagemServiceBean;

	@PostConstruct
	public void init() {
		destinatarios = usuarioServiceBean.buscarTodos();
	}
	
	public String enviarMensagem() {
		List<Usuario> usuariosSelecionados = new ArrayList<Usuario>();
		for (String idUsuario : destinatariosSelecionadas) {
			usuariosSelecionados.add(usuarioServiceBean.consultarUsuarioPorId(Long.valueOf(idUsuario)));
		}
		mensagem.setDtEnvio(new Date());
		mensagem.setRemetente(UsuarioSessionBean.getInstance().getUsuarioSessao());
		mensagem.setTipo(Constantes.MSG_PRINCIPAL);
		try {
			mensagemServiceBean.enviarMensagem(mensagem);
			exibirMsgSucesso("Mensagem enviada com sucesso!");
			return Tela.CAIXA_ENTRADA;
		} catch (Exception e) {
			exibirMsgErro(e.getMessage());
			return null;
		}
	}
	
	
	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public List<Usuario> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<Usuario> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public List<String> getDestinatariosSelecionadas() {
		return destinatariosSelecionadas;
	}

	public void setDestinatariosSelecionadas(List<String> destinatariosSelecionadas) {
		this.destinatariosSelecionadas = destinatariosSelecionadas;
	}

}