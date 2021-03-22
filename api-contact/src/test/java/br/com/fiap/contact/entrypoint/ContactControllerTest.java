package br.com.fiap.contact.entrypoint;

import static io.restassured.RestAssured.*;

import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ContactControllerTest {

//	@Test
	public void testFindById() {
		given()
			.pathParam("id", "60579b21b2d3c70fc9082866")
			.when().get("/contacts/{id}")
			.then()
				.statusCode(Status.OK.getStatusCode());
	}
	
//	@Test
	public void testfindByIdPessoaUmEDois() {
		given()
			.queryParam("idPessoaUm", "3")
			.queryParam("idPessoaDois", "5")
			.when().get("/contacts")
			.then()
				.statusCode(Status.OK.getStatusCode());
	}
}
