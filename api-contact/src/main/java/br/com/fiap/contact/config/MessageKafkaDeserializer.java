package br.com.fiap.contact.config;

import br.com.fiap.contact.entity.MessageKafka;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class MessageKafkaDeserializer extends JsonbDeserializer<MessageKafka> {

	public MessageKafkaDeserializer() {
		super(MessageKafka.class);
	}

	
}
