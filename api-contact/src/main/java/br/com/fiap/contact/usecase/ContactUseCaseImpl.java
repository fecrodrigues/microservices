package br.com.fiap.contact.usecase;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import org.bson.Document;

import br.com.fiap.contact.dataprovider.ContactRepository;
import br.com.fiap.contact.entity.Contact;
import br.com.fiap.contact.entity.Message;

@ApplicationScoped
public class ContactUseCaseImpl implements ContactUseCase {

	private final ContactRepository repository;
	
	public ContactUseCaseImpl(ContactRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Contact findById(String id) {
			Contact contact = repository.find("_id",id).firstResultOptional()
					.orElseThrow(() -> new NotFoundException("Not found contact from id "+id+"!"));
			
			return contact;
	}

	@Override
	public Contact findByIdPessoaUmEDois(String idPessoaUm, String idPessoaDois) {
		Document doc = new Document();
		doc.put("idPessoaUm", idPessoaUm);
		doc.put("idPessoaDois", idPessoaDois);
		
		
		return repository.find(doc).firstResultOptional()
				.orElseThrow(() -> new NotFoundException("Not found contact from person "+idPessoaUm+" and "+idPessoaDois+"!"));
	}

	@Override
	public Contact createContact(Contact contact) {
		repository.persist(contact);
		
		return findById(contact.getId());
	}

	@Override
	public Message createMessage(String idContact, Message msg) {
		Contact contact = findById(idContact);

		contact.getMessages().add(msg);
		
		repository.persistOrUpdate(contact);
		
		return findById(idContact).getMessages().stream()
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
		Stream<Message> streamMessages =  contact.getMessages().stream();
		
		if(streamMessages.anyMatch(message -> message.getId().equals(idMessage))) {
			throw new NotFoundException("Not found message from id "+idMessage);
		}
			
		List<Message> newMessages = streamMessages
				.filter(message -> !message.getId().equals(idMessage))
				.collect(Collectors.toList());
	
		
		contact.setMessages(newMessages);
		repository.persistOrUpdate(contact);
	}

}
