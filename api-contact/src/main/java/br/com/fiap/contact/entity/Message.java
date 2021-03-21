package br.com.fiap.contact.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import br.com.fiap.contact.dto.MessageReq;

public class Message {

	private String id;
	private String idEmissor;
	private String descricao;
	private LocalDateTime dataDeCriacao;
	
	public Message() {}
	
	public Message(MessageReq msgReq) {
		this.id = new ObjectId().toHexString();
		this.idEmissor = msgReq.getIdEmissor();
		this.descricao = msgReq.getDescricao();
		this.dataDeCriacao = msgReq.getDataDeCriacao();
	}
	
	public Message(MessageKafka msgKafka) {
		this.id = new ObjectId().toHexString();
		this.idEmissor = msgKafka.getIdEmissor();
		this.descricao = msgKafka.getDescricao();
		this.dataDeCriacao = msgKafka.getDataDeCriacao();
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
