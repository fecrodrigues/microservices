package br.com.fiap.contact.dto;

import java.time.LocalDateTime;

public class MessageReq {

	private String idEmissor;
	private String descricao;
	private LocalDateTime dataDeCriacao;
	
	
	public String getIdEmissor() {
		return idEmissor;
	}
	public void setIdEmissor(String idEmissor) {
		this.idEmissor = idEmissor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDateTime getDataDeCriacao() {
		return dataDeCriacao;
	}
	public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}

}
