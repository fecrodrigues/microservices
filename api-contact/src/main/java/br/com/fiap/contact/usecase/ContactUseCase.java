package br.com.fiap.contact.usecase;

import javax.ws.rs.core.Response;

import br.com.fiap.contact.entity.Contact;
import br.com.fiap.contact.entity.Message;

public interface ContactUseCase {

	public Response findById(String id);
	public Contact findByIdPessoaUmEDois(String idPessoaUm, String idPessoaDois);
	public Contact createContact(Contact contact);
	public Message createMessage(String idContact, Message msg);
	public void removeContacts(String id);
	public void removeMessage(String idContact, String idMessage);
}
