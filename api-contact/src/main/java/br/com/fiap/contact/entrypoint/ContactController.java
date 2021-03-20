package br.com.fiap.contact.entrypoint;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.specimpl.ResteasyUriBuilderImpl;

import br.com.fiap.contact.dto.ContactReq;
import br.com.fiap.contact.dto.ContactResp;
import br.com.fiap.contact.dto.MessageReq;
import br.com.fiap.contact.dto.MessageResp;
import br.com.fiap.contact.entity.Contact;
import br.com.fiap.contact.entity.Message;
import br.com.fiap.contact.usecase.ContactUseCase;

@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class ContactController {

	private final ContactUseCase useCase;
	private final ResteasyUriBuilderImpl uriBuilder;
	
	public ContactController(ContactUseCase useCase) {
		this.useCase = useCase;
		this.uriBuilder = new ResteasyUriBuilderImpl();
	}
	
	@GET
	@Path("/{id}")
	public Response findById(@PathParam(value = "id") String id) {		
		return Response.ok().entity(
				new ContactResp(useCase.findById(id)
		)).build();
	}
	
	@GET
	public Response findByIdPessoaUmEDois(
				@QueryParam(value = "idPessoaUm") String idPessoaUm,
				@QueryParam(value = "idPessoaDois") String idPessoaDois
	) {
		return Response.ok().entity(new ContactResp(
				useCase.findByIdPessoaUmEDois(idPessoaUm, idPessoaDois)
		)).build();
	}
	
	@POST
	public Response createContact(ContactReq contactReq) {
		ContactResp contactResp = new ContactResp(
				useCase.createContact(new Contact(contactReq))
		);
		
		
		return Response.created(
				uriBuilder.path("/contacts/{id}").build(contactResp.getId())
		).entity(contactResp).build();
				
	}

	@POST
	@Path("/{idContact}/messages")
	public Response createMessage(@PathParam(value = "idContact") String idContact, MessageReq msgReq) {
		MessageResp messageResp = new MessageResp(
				useCase.createMessage(idContact, new Message(msgReq))
		);
		
		
		return Response.created(
				uriBuilder.path("{id}/messages/{id}").build(idContact, messageResp.getId())
		).entity(messageResp).build();
	}
	
	@DELETE
	@Path("/{id}")
	public void removeContact(@PathParam(value = "id") String id) {
		useCase.removeContacts(id);
	}

	@DELETE
	@Path("/{idContact}/messages/{idMessage}")
	public void removeMessage(@PathParam(value = "idContact") String idContact, @PathParam(value = "idMessage") String idMessage) {
		useCase.removeMessage(idContact, idMessage);
	}
	
}
