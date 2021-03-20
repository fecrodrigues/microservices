package br.com.fiap.contact.dataprovider;

import javax.enterprise.context.ApplicationScoped;

import br.com.fiap.contact.entity.Contact;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

@ApplicationScoped
public interface ContactRepository extends PanacheMongoRepository<Contact> {

}
