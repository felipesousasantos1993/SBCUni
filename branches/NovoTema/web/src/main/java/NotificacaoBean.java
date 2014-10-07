import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.mensagem.entity.Mensagem;
import br.com.sbcuni.mensagem.service.MensagemServiceBean;
import br.com.sbcuni.topico.entity.Topico;
import br.com.sbcuni.topico.service.TopicoServiceBean;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.usuario.service.UsuarioServiceBean;
import br.com.sbcuni.util.Util;

@SessionScoped
@ManagedBean
public class NotificacaoBean extends GenericBean {

	private static final long serialVersionUID = 6818220719175636174L;

	public NotificacaoBean() {
		super();
	}

	@EJB
	private UsuarioServiceBean usuarioServiceBean;
	@EJB
	private MensagemServiceBean mensagemServiceBean;
	@EJB
	private TopicoServiceBean topicoServiceBean;

	private List<Mensagem> notificaoMensagens;
	private List<Topico> notificaoTopicos;
	private Usuario usuarioSessao;

	@PostConstruct
	public void init() {
		usuarioSessao = UsuarioSessionBean.getInstance().getUsuarioSessao();
		carregarNotificacoes();
	}

	public void carregarNotificacoes() {
		carregarNotificacaoMensagens();
	}

	public void carregarNotificacaoMensagens() {
		if (Util.isNull(UsuarioSessionBean.getDataAcesso())) {
			notificaoMensagens = mensagemServiceBean.consultarMensagemNotificacao(new Date(), usuarioSessao.getIdUsuario());
		} else {
			notificaoMensagens = mensagemServiceBean.consultarMensagemNotificacao(UsuarioSessionBean.getDataAcesso(), usuarioSessao.getIdUsuario());
		}
	}

	public Integer getNuNotificacaoMensagens() {
		if (!Util.isNull(getNotificaoMensagens())) {
			return getNotificaoMensagens().size();
		} else {
			return 0;
		}
	}

	public Integer getNuNotificacaoTopicos() {
		if (!Util.isNull(getNotificaoTopicos())) {
			return getNotificaoTopicos().size();
		} else {
			return 0;
		}
	}

	public List<Mensagem> getNotificaoMensagens() {
		return notificaoMensagens;
	}

	public void setNotificaoMensagens(List<Mensagem> notificaoMensagens) {
		this.notificaoMensagens = notificaoMensagens;
	}

	public List<Topico> getNotificaoTopicos() {
		return notificaoTopicos;
	}

	public void setNotificaoTopicos(List<Topico> notificaoTopicos) {
		this.notificaoTopicos = notificaoTopicos;
	}
}
