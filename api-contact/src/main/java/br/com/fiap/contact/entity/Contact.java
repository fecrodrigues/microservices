package br.com.fiap.contact.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import br.com.fiap.contact.dto.ContactReq;
import io.quarkus.mongodb.panache.MongoEntity;

@MongoEntity(collection = "CONTACTCS")
public class Contact {

	@BsonId
	private String id;
	private String idPessoaUm;
	private String idPessoaDois;
	private List<Message> messages;
	
	
	public Contact() {}
	
	public Contact(ContactReq req) {
		this.id = ObjectId.get().toHexString();
		this.idPessoaUm = req.getIdPessoaUm();
		this.idPessoaDois = req.getIdPessoaDois();
		
		List<Message> listMsg = new ArrayList<>();
		req.getMensagens().forEach(msg -> {
			Message msgEntity = new Message();
			msgEntity.setId(ObjectId.get().toHexString());
			msgEntity.setIdEmissor(msg.getIdEmissor());
			msgEntity.setDescricao(msg.getDescricao());
			msgEntity.setDataDeCriacao(msg.getDataDeCriacao());
			
			listMsg.add(msgEntity);
		});
		this.messages = listMsg;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdPessoaUm() {
		return idPessoaUm;
	}
	public void setIdPessoaUm(String idPessoaUm) {
		this.idPessoaUm = idPessoaUm;
	}
	public String getIdPessoaDois() {
		return idPessoaDois;
	}
	public void setIdPessoaDois(String idPessoaDois) {
		this.idPessoaDois = idPessoaDois;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
}
