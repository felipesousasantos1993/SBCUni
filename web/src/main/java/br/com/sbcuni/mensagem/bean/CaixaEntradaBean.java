package br.com.sbcuni.mensagem.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Constantes;
import br.com.sbcuni.mensagem.entity.Mensagem;
import br.com.sbcuni.mensagem.service.MensagemServiceBean;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;

@ManagedBean
@ViewScoped
public class CaixaEntradaBean extends GenericBean {

	private static final long serialVersionUID = 1894538838110348372L;

	private List<Mensagem> mensagemsRecebidas;
	private List<Mensagem> mensagemEnviadas;
	private List<Mensagem> mensagensExcluidas = new ArrayList<Mensagem>();
	
	@EJB
	private MensagemServiceBean mensagemServiceBean;

	private Boolean msgRecebidas = Boolean.FALSE;
	private Boolean msgEnviadas = Boolean.FALSE;
	private Boolean msgExcluidas = Boolean.FALSE;
	
	private Integer nuMsgRecebidas;
	private Integer nuMsgEnviadas;
	private Integer nuMsgExcluidas;
	
	@PostConstruct
	public void init() {
		mensagemsRecebidas = mensagemServiceBean.consultarCaixaEntradaUsuario(UsuarioSessionBean.getInstance().getUsuarioSessao());
		mensagemEnviadas = mensagemServiceBean.consultarMensagemEnviadasUsuario(UsuarioSessionBean.getInstance().getUsuarioSessao());
		msgRecebidas = Boolean.TRUE;
		for (Mensagem mensagem : mensagemsRecebidas) {
			if (mensagem.getTipo().equals(Constantes.MSG_LIXIERA)) {
				mensagemsRecebidas.remove(mensagem);
				mensagensExcluidas.add(mensagem);
			}
		}
	}
	
	public void selecionarTodasMsgs() {
		if (msgRecebidas) {
			selecionarTodasMensagem(mensagemsRecebidas);
		} else if (msgEnviadas) {
			selecionarTodasMensagem(mensagemEnviadas);
		} else if (msgExcluidas) {
			selecionarTodasMensagem(mensagensExcluidas);
		}
	}
	public void deselecionarTodasMsgs() {
		if (msgRecebidas) {
			deselecionarTodasMensagem(mensagemsRecebidas);
		} else if (msgEnviadas) {
			deselecionarTodasMensagem(mensagemEnviadas);
		} else if (msgExcluidas) {
			deselecionarTodasMensagem(mensagensExcluidas);
		}
	}
	
	// SELECIONAR
	
	public void selecionarTodasMensagem(List<Mensagem> lista) {
		for (Mensagem m : lista) {
			m.setSelecionada(Boolean.TRUE);
		}
	}
	public void deselecionarTodasMensagem(List<Mensagem> lista) {
		for (Mensagem m : lista) {
			m.setSelecionada(Boolean.FALSE);
		}
	}
	
	// MARCAR
	
	
	public void exibirMsgsRecebidas() {
		msgRecebidas = Boolean.TRUE;
		msgEnviadas = Boolean.FALSE;
		msgExcluidas = Boolean.FALSE;
	}
	
	public void exibirMsgsEnviadas() {
		msgRecebidas = Boolean.FALSE;
		msgEnviadas = Boolean.TRUE;
		msgExcluidas = Boolean.FALSE;
	}
	
	public void exibirMsgsExcluidas() {
		msgRecebidas = Boolean.FALSE;
		msgEnviadas = Boolean.FALSE;
		msgExcluidas = Boolean.TRUE;
	}


	public List<Mensagem> getMensagemsRecebidas() {
		return mensagemsRecebidas;
	}

	public void setMensagemsRecebidas(List<Mensagem> mensagemsRecebidas) {
		this.mensagemsRecebidas = mensagemsRecebidas;
	}

	public List<Mensagem> getMensagemEnviadas() {
		return mensagemEnviadas;
	}

	public void setMensagemEnviadas(List<Mensagem> mensagemEnviadas) {
		this.mensagemEnviadas = mensagemEnviadas;
	}


	public Boolean getMsgRecebidas() {
		return msgRecebidas;
	}


	public void setMsgRecebidas(Boolean msgRecebidas) {
		this.msgRecebidas = msgRecebidas;
	}


	public Boolean getMsgEnviadas() {
		return msgEnviadas;
	}


	public void setMsgEnviadas(Boolean msgEnviadas) {
		this.msgEnviadas = msgEnviadas;
	}


	public Boolean getMsgExcluidas() {
		return msgExcluidas;
	}


	public void setMsgExcluidas(Boolean msgExcluidas) {
		this.msgExcluidas = msgExcluidas;
	}


	public Integer getNuMsgRecebidas() {
		return getMensagemsRecebidas().size();
	}


	public void setNuMsgRecebidas(Integer nuMsgRecebidas) {
		this.nuMsgRecebidas = nuMsgRecebidas;
	}


	public Integer getNuMsgEnviadas() {
		return getMensagemEnviadas().size();
	}


	public void setNuMsgEnviadas(Integer nuMsgEnviadas) {
		this.nuMsgEnviadas = nuMsgEnviadas;
	}


	public Integer getNuMsgExcluidas() {
		return getMensagensExcluidas().size();
	}


	public void setNuMsgExcluidas(Integer nuMsgExcluidas) {
		this.nuMsgExcluidas = nuMsgExcluidas;
	}

	public List<Mensagem> getMensagensExcluidas() {
		return mensagensExcluidas;
	}

	public void setMensagensExcluidas(List<Mensagem> mensagensExcluidas) {
		this.mensagensExcluidas = mensagensExcluidas;
	}
	
}
