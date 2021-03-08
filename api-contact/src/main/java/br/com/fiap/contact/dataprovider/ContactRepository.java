package br.com.fiap.contact.dataprovider;

import org.springframework.stereotype.Repository;

import br.com.fiap.contact.entity.Contact;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

@Repository
public interface ContactRepository extends PanacheMongoRepository<Contact> {

}
