package br.com.fiap.contact.entrypoint;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import br.com.fiap.contact.entity.Message;
import br.com.fiap.contact.entity.MessageKafka;
import br.com.fiap.contact.usecase.ContactUseCase;

@ApplicationScoped
public class KafkaConsumer {

	@Inject
	private final ContactUseCase useCase;
	
	public KafkaConsumer(ContactUseCase useCase) {
		this.useCase = useCase;
	}
	
	@Incoming("contact-messages")
	public void consumeTopic(MessageKafka msgKafka) {
		useCase.createMessage(msgKafka.getIdContact(), new Message(msgKafka));
	}
}
