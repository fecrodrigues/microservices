package br.com.fiap.contact.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.stereotype.Service;

import br.com.fiap.contact.dataprovider.ContactRepository;
import br.com.fiap.contact.entity.Contact;
import br.com.fiap.contact.entity.Message;

@Service
public class ContactUseCaseImpl implements ContactUseCase {

	private final ContactRepository repository;
	
	public ContactUseCaseImpl(ContactRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Contact findById(String id) {
		return repository.find("_id",id).firstResult();
	}

	@Override
	public Contact findByIdPessoaUmEDois(String idPessoaUm, String idPessoaDois) {
		Document doc = new Document();
		doc.put("idPessoaUm", idPessoaUm);
		doc.put("idPessoaDois", idPessoaDois);
		
		
		return repository.find(doc).firstResult();
	}

	@Override
	public Contact createContact(Contact contact) {
		repository.persist(contact);
		
		return findById(contact.getId());
	}

	@Override
	public Message createMessage(String idConact, Message msg) {
		Contact contact = findById(idConact);

		contact.getMessages().add(msg);
		
		repository.persistOrUpdate(contact);
		
		return findById(idConact).getMessages().stream()
				.filter(message -> message.getId().equals(msg.getId()))
				.findFirst().get();
		
	}
	
	@Override
	public void removeContacts(String id) {
		repository.delete("_id", id);
	}

	@Override
	public void removeMessage(String idContact, String idMessage) {
		Contact contact = findById(idContact);

		List<Message> newMessages = contact.getMessages().stream()
					.filter(message -> !message.getId().equals(idMessage))
					.collect(Collectors.toList());
		
		contact.setMessages(newMessages);
		repository.persistOrUpdate(contact);
	}

}
