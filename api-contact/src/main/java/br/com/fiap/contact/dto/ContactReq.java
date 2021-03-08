package br.com.fiap.contact.dto;

import java.util.List;

public class ContactReq {
	
	private String idPessoaUm;
	private String idPessoaDois;
	private List<MessageReq> mensagens;
	
	
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
	public List<MessageReq> getMensagens() {
		return mensagens;
	}
	public void setMensagens(List<MessageReq> mensagens) {
		this.mensagens = mensagens;
	}
	
}
