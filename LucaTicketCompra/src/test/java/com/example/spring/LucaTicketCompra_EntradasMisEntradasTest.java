package com.example.spring;

import static io.restassured.RestAssured.given;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

/**
 * @ClassName LucaTicketCompra_EntradasMisEntradasTest
 *
 * @author Patricia García, Usoa Larrarte
 *
 * @date 15 jul. 2021
 * 
 * @version 1.0
 */
public class LucaTicketCompra_EntradasMisEntradasTest {

	private static final Logger logger = Logger.getLogger("LucaTicketCompra_EntradasMisEntradasTest");

	@Test
	void entradasMisEntradasTest() throws Exception {
	
		logger.info("---- Se ha invocado el método EntradasMisEntradasTest");

			given()
			.port(4444)
			.header("Content-type", "application/json")
			.and()
			.queryParam("mail", "admin@gmail.com")
			.queryParam("password", "1234")
		.when()
			.post("/entradas/misentradas")
		.then().log().all()
			.assertThat()
			.statusCode(200);
	}

	
}
