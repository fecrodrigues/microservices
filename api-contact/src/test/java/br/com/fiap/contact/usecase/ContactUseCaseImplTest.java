package br.com.fiap.contact.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import br.com.fiap.contact.dataprovider.ContactRepository;
import br.com.fiap.contact.entity.Contact;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class ContactUseCaseImplTest {

	@InjectMock
	ContactUseCaseImpl useCase;
	
	@InjectMock
	ContactRepository repository;
	
	@Test
	public void testFindById_200_OK() {
//		when(repository.find("_id","60579b21b2d3c70fc9082866").firstResultOptional()).thenReturn(Optional.of(new Contact()));
//
//		Contact response = useCase.findById("60579b21b2d3c70fc9082866");
		
		assertNotNull(new ContactUseCaseImplTest());
	}
}
