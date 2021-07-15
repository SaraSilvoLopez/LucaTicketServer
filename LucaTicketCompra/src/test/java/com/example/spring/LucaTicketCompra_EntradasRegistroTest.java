package com.example.spring;

import static io.restassured.RestAssured.given;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

/**
 * @ClassName LucaTicketCompra_EntradasRegistroTest
 *
 * @author Patricia García, Usoa Larrarte
 *
 * @date 15 jul. 2021
 * 
 * @version 1.0
 */
public class LucaTicketCompra_EntradasRegistroTest {

	private static final Logger logger = Logger.getLogger("LucaTicketCompra_EntradasRegistroTest");

	String token = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjI2MzYxMTQ3LCJleHAiOjE2MjYzNzkxNDcsIm5vbWJyZSI6IkFkbWluIiwiYXBlbGxpZG8iOiJBZG1pbiIsInJvbCI6IkFETUlOIn0.WcDn3fHvsNJ7lJKzHFggmkF5mIGL-qhDHlanRUzzNbZ1Fzj43U4PdyagkL4gKP4gjtxeDg18ME5C87Aq7qTAuQ";

	@Test
	void entradasRegistroTest() throws Exception {
	
		logger.info("---- Se ha invocado el método EntradasRegistroTest");
		
			given()
				.port(4444)
				.header("Content-type", "application/json")
				.header("Authorization", token)
				.header("token", token)
				.body("{\"usuarioId\":1, \"eventoId\":\"60e83425fad2c642fd2725d2\"}")
			.when()
				.post("/entradas/registro")
			.then().log().all()
				.assertThat()
				.statusCode(200);
	
	}

}
