package br.com.fiap.contact.dto;

import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbAnnotation;
import br.com.fiap.contact.entity.Contact;

@JsonbAnnotation
public class ContactResp {

	private String id;
	private String idPessoaUm;
	private String idPessoaDois;
	private List<MessageResp> mensagens;
	
	public ContactResp() {}

	public ContactResp(Contact entity) {
		this.id = entity.getId();
		this.idPessoaUm = entity.getIdPessoaUm();
		this.idPessoaDois = entity.getIdPessoaDois();
		
		List<MessageResp> listMsgResp = new ArrayList<>();
		entity.getMessages().forEach(msg -> {
			MessageResp msgResp = new MessageResp();
			msgResp.setId(msg.getId());
			msgResp.setIdEmissor(msg.getIdEmissor());
			msgResp.setDescricao(msg.getDescricao());
			msgResp.setDataDeCriacao(msg.getDataDeCriacao());
			
			listMsgResp.add(msgResp);
		});
		this.mensagens = listMsgResp;
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
	public List<MessageResp> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<MessageResp> mensagens) {
		this.mensagens = mensagens;
	}
	
}
