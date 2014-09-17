package br.com.sbcuni.grupoEstudo.bean;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sbcuni.bean.GenericBean;
import br.com.sbcuni.constantes.Tela;
import br.com.sbcuni.exception.SbcuniException;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.grupoEstudo.service.GrupoEstudoSerivceBean;
import br.com.sbcuni.usuario.bean.UsuarioSessionBean;
import br.com.sbcuni.util.WebResources;

@ManagedBean
@ViewScoped
public class CriarGrupoEstudoBean extends GenericBean {

	private static final long serialVersionUID = 2926805709691198033L;

	public CriarGrupoEstudoBean() {
		super();
	}
	
	@EJB
	private GrupoEstudoSerivceBean grupoEstudoSerivceBean;
	
	private GrupoEstudo grupoEstudo = new GrupoEstudo();
	
	public String criarGrupo() {
		grupoEstudo.setDtCriacao(new Date());
		grupoEstudo.setProfessor(UsuarioSessionBean.getInstance().getUsuarioSessao());
		try {
			grupoEstudoSerivceBean.criarGrupoEstudo(grupoEstudo);
			exibirMsgSucesso(getMensagem("display.grupo.criado.sucesso", WebResources.MENSAGEM));
			return Tela.MEUS_GRUPOS;
		} catch (SbcuniException e) {
			exibirMsgErro(e.getMessage());
			return null;
		}
	}

	public GrupoEstudo getGrupoEstudo() {
		return grupoEstudo;
	}

	public void setGrupoEstudo(GrupoEstudo grupoEstudo) {
		this.grupoEstudo = grupoEstudo;
	}
	
}
