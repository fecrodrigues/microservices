package br.com.fiap.contact.dto;

import java.time.LocalDateTime;

import br.com.fiap.contact.entity.Message;

public class MessageResp {

	private String id;
	private String idEmissor;
	private String descricao;
	private LocalDateTime dataDeCriacao;
	
	
	public MessageResp() {}
	
	public MessageResp(Message message) {
		this.id = message.getId();
		this.idEmissor = message.getIdEmissor();
		this.descricao = message.getDescricao();
		this.dataDeCriacao = message.getDataDeCriacao();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
