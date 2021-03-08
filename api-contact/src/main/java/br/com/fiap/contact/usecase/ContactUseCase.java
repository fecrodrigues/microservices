package br.com.fiap.contact.usecase;

import br.com.fiap.contact.entity.Contact;
import br.com.fiap.contact.entity.Message;

public interface ContactUseCase {

	public Contact findById(String id);
	public Contact findByIdPessoaUmEDois(String idPessoaUm, String idPessoaDois);
	public Contact createContact(Contact contact);
	public Message createMessage(String idContact, Message msg);
	public void removeContacts(String id);
	public void removeMessage(String idContact, String idMessage);
}
