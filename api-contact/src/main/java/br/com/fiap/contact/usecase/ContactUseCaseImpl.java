package br.com.fiap.contact.usecase;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.bson.Document;
import org.springframework.stereotype.Service;

import br.com.fiap.contact.dataprovider.ContactRepository;
import br.com.fiap.contact.dto.ErrorResp;
import br.com.fiap.contact.entity.Contact;
import br.com.fiap.contact.entity.Message;

@ApplicationScoped
@Service
public class ContactUseCaseImpl implements ContactUseCase {

	private final ContactRepository repository;
	
	public ContactUseCaseImpl(ContactRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Response findById(String id) {
			Optional<Contact> opt = repository.find("_id",id).firstResultOptional();
			
			if(opt.isEmpty()) {
				return Response.status(Status.NOT_FOUND)
						.entity(new ErrorResp("not_found", "Not found contact from id "+id+"!"))
						.build();
			}
			
			return Response.ok(opt.get()).build();
	}

	@Override
	public Contact findByIdPessoaUmEDois(String idPessoaUm, String idPessoaDois) {
		Document doc = new Document();
		doc.put("idPessoaUm", idPessoaUm);
		doc.put("idPessoaDois", idPessoaDois);
		
		
		return repository.find(doc).firstResultOptional()
				.orElseThrow(() -> new NotFoundException("Not found contact from person "+idPessoaUm+" and "+"!"));
	}

	@Override
	public Contact createContact(Contact contact) {
		repository.persist(contact);
		
		return (Contact) findById(contact.getId()).getEntity();
	}

	@Override
	public Message createMessage(String idContact, Message msg) {
		Contact contact = (Contact) findById(idContact).getEntity();

		contact.getMessages().add(msg);
		
		repository.persistOrUpdate(contact);
		
		return ((Contact) findById(idContact).getEntity()).getMessages().stream()
				.filter(message -> message.getId().equals(msg.getId()))
				.findFirst().get();
		
	}
	
	@Override
	public void removeContacts(String id) {
		repository.delete("_id", id);
	}

	@Override
	public void removeMessage(String idContact, String idMessage) {
		Contact contact = (Contact) findById(idContact).getEntity();

		List<Message> newMessages = contact.getMessages().stream()
					.filter(message -> !message.getId().equals(idMessage))
					.collect(Collectors.toList());
		
		contact.setMessages(newMessages);
		repository.persistOrUpdate(contact);
	}

}
