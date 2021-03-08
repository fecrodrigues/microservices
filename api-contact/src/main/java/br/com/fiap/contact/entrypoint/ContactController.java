package br.com.fiap.contact.entrypoint;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.contact.dto.ContactReq;
import br.com.fiap.contact.dto.ContactResp;
import br.com.fiap.contact.dto.MessageReq;
import br.com.fiap.contact.dto.MessageResp;
import br.com.fiap.contact.entity.Contact;
import br.com.fiap.contact.entity.Message;
import br.com.fiap.contact.usecase.ContactUseCase;

@RestController
@RequestMapping("/contacts")
public class ContactController {

	private final ContactUseCase useCase;
	
	public ContactController(ContactUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ContactResp findById(@PathVariable String id) {		
		return new ContactResp(
				useCase.findById(id)
		);
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ContactResp findByIdPessoaUmEDois(
				@RequestParam String idPessoaUm,
				@RequestParam String idPessoaDois
	) {
		return new ContactResp(
				useCase.findByIdPessoaUmEDois(idPessoaUm, idPessoaDois)
		);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ContactResp createContact(@RequestBody ContactReq contactReq) {
		return new ContactResp(
				useCase.createContact(new Contact(contactReq))
		);
	}

	@PostMapping("/{idContact}/messages")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MessageResp createMessage(@PathVariable String idContact, @RequestBody MessageReq msgReq) {
		return new MessageResp(
				useCase.createMessage(idContact, new Message(msgReq))
		);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeContact(@PathVariable String id) {
		useCase.removeContacts(id);
	}

	
	@DeleteMapping("/{idContact}/messages/{idMessage}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void removeMessage(@PathVariable String idContact, @PathVariable String idMessage) {
		useCase.removeMessage(idContact, idMessage);
	}
}
