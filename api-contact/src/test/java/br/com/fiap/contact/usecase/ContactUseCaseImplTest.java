package br.com.fiap.contact.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.contact.dataprovider.ContactRepositoryImpl;
import br.com.fiap.contact.entity.Contact;
import br.com.fiap.contact.entity.Message;
import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
@SuppressWarnings("unchecked")
public class ContactUseCaseImplTest {

	@Inject
	ContactUseCaseImpl useCase;
	
	@InjectMock
	ContactRepositoryImpl repository;
	
	private PanacheQuery<Contact> mockQuery;
	private Contact contactMock;
	private Document docMock;
	private Message msgMock;
	
	@BeforeEach
    public void init() {
    	mockQuery = mock(PanacheQuery.class);
    	
    	contactMock = new Contact();
    	contactMock.setId("60579b21b2d3c70fc9082866");
    	contactMock.setIdPessoaUm("60579b5db2d3c70fc9082869");
    	contactMock.setIdPessoaDois("60579f0c63ab07151194fb70");
    	contactMock.setMessages(new ArrayList<Message>());
    	
		docMock = new Document();
		docMock.put("idPessoaUm", "60579b5db2d3c70fc9082869");
		docMock.put("idPessoaDois", "60579f0c63ab07151194fb70");
		
		msgMock = new Message();
		msgMock.setId("60579ce7b2d3c70fc908286a");
		msgMock.setIdEmissor("60446b47bd50fa4971408870");
		msgMock.setDescricao("Tudo bem?");
		msgMock.setDataDeCriacao(LocalDateTime.now());
    }
	
	
//	@Test
	public void testFindById_200() {
		when(repository.find("_id","60579b21b2d3c70fc9082866")).thenReturn(mockQuery);
		when(mockQuery.firstResultOptional()).thenReturn(Optional.of(contactMock));
		
		Contact response = useCase.findById("60579b21b2d3c70fc9082866");
		
		assertNotNull(response);
		assertTrue(response.getId().equals("60579b21b2d3c70fc9082866"));
		assertTrue(response.getIdPessoaUm().equals("60579b5db2d3c70fc9082869"));
		assertTrue(response.getIdPessoaDois().equals("60579f0c63ab07151194fb70"));
		assertTrue(response.getMessages().isEmpty());
	}
	
//	@Test
	public void testFindById_204() {
		when(repository.find("_id","60579b21b2d3c70fc9082880")).thenReturn(mockQuery);
		when(mockQuery.firstResultOptional()).thenReturn(Optional.empty());
		
		Exception exception = assertThrows(NotFoundException.class, () -> {
			useCase.findById("60579b21b2d3c70fc9082880");
		});
		
		assertNotNull(exception);
		assertTrue(exception instanceof NotFoundException);
		assertTrue(exception.getMessage().equals("Not found contact from id 60579b21b2d3c70fc9082880!"));
	}
	
//	@Test
	public void testFindByIdPessoaUmEDois_200() {
		when(repository.find(docMock)).thenReturn(mockQuery);
		when(mockQuery.firstResultOptional()).thenReturn(Optional.of(contactMock));
		
		Contact response = useCase.findByIdPessoaUmEDois("60579b5db2d3c70fc9082869","60579f0c63ab07151194fb70");
		
		assertNotNull(response);
		assertTrue(response.getId().equals("60579b21b2d3c70fc9082866"));
		assertTrue(response.getIdPessoaUm().equals("60579b5db2d3c70fc9082869"));
		assertTrue(response.getIdPessoaDois().equals("60579f0c63ab07151194fb70"));
		assertTrue(response.getMessages().isEmpty());
	}
	
//	@Test
	public void testFindByIdPessoaUmEDois_204() {
		when(repository.find(docMock)).thenReturn(mockQuery);
		when(mockQuery.firstResultOptional()).thenReturn(Optional.empty());
		
		Exception exception = assertThrows(NotFoundException.class, () -> {
			useCase.findByIdPessoaUmEDois("60579b5db2d3c70fc9082869","60579f0c63ab07151194fb70");
		});
		
		assertNotNull(exception);
		assertTrue(exception instanceof NotFoundException);
		assertTrue(exception.getMessage().equals("Not found contact from person 60579b5db2d3c70fc9082869 and 60579f0c63ab07151194fb70!"));
	}
	
//	@Test
	public void testCreateContact_201() {
		doNothing().when(repository).persist(contactMock);
		when(repository.find("_id",contactMock.getId())).thenReturn(mockQuery);
		when(mockQuery.firstResultOptional()).thenReturn(Optional.of(contactMock));
		
		Contact response = useCase.createContact(contactMock);
		
		assertNotNull(response);
		assertTrue(response.getId().equals("60579b21b2d3c70fc9082866"));
		assertTrue(response.getIdPessoaUm().equals("60579b5db2d3c70fc9082869"));
		assertTrue(response.getIdPessoaDois().equals("60579f0c63ab07151194fb70"));
		assertTrue(response.getMessages().isEmpty());
	}
	
//	@Test
	public void testCreateMessage_201() {
		doNothing().when(repository).persistOrUpdate(contactMock);
		when(repository.find("_id",contactMock.getId())).thenReturn(mockQuery);
		when(mockQuery.firstResultOptional()).thenReturn(Optional.of(contactMock));
		
		useCase.createMessage(contactMock.getId(), msgMock);
		
		assertFalse(contactMock.getMessages().isEmpty());
		assertEquals(contactMock.getMessages().get(0).getDescricao(), msgMock.getDescricao());
	}
}
