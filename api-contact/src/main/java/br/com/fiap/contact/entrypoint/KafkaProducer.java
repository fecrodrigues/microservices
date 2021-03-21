package br.com.fiap.contact.entrypoint;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

import br.com.fiap.contact.entity.MessageKafka;

@ApplicationScoped
@Path("/kafka/producer")
public class KafkaProducer {

	@Inject 
	@Channel("create-message") Emitter<MessageKafka> msgEmitter;
	@OnOverflow(value = OnOverflow.Strategy.BUFFER, bufferSize = 1000)

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addMessage(MessageKafka messageKafka) {
        msgEmitter.send(messageKafka);
    }

//	Stream to geneate messages in looping	
//	@Outgoing("generate-messages")
//	public Multi<MessageKafka> geneateMessages() {
//		MessageKafka msg = new MessageKafka();
//		
//		msg.setIdContact("6053eac91bf4f7332cfedca0");
//		msg.setIdEmissor("60446b47bd50fa4971408870");
//		msg.setDescricao("Tudo bem e com vc?");
//		msg.setDataDeCriacao(LocalDateTime.now());
//		
//        return Multi.createFrom().ticks().every(Duration.ofMinutes(1))
//                .onOverflow().drop()
//                .map(tick -> msg);
//	}
	

}
