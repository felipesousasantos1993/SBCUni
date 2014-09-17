package br.com.sbcuni.mensagem.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Anexo implements Serializable {

	private static final long serialVersionUID = -16536330303310635L;

	@Id
    @GeneratedValue
    private long id;

    @Column(name = "noArquivo", length = 50, nullable = false)
    private String noArquivo;

    @Column(name = "lida", nullable = false)
    private Boolean lida;
    
    @Column(name = "tipo", length = 30, nullable = false)
    private String tipo;

    @Column(name = "tamanho", nullable = false)
    private Long tamanho;
    
    @Column(name = "extensao", nullable = false)
    private String extensao;

    @Lob
    private String arquivo;
    
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = Mensagem.class)
    private Mensagem mensagem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNoArquivo() {
		return noArquivo;
	}

	public void setNoArquivo(String noArquivo) {
		this.noArquivo = noArquivo;
	}

	public Boolean getLida() {
		return lida;
	}

	public void setLida(Boolean lida) {
		this.lida = lida;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

}
