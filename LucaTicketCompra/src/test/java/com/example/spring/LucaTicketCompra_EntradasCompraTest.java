package com.example.spring;

import static io.restassured.RestAssured.given;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;

/**
 * @ClassName LucaTicketCompra_EntradasCompraTest
 *
 * @author Patricia García, Usoa Larrarte
 *
 * @date 15 jul. 2021
 * 
 * @version 1.0
 */
public class LucaTicketCompra_EntradasCompraTest {

	private static final Logger logger = Logger.getLogger("LucaTicketCompra_EntradasCompraTest");
	
	@Test
	void entradasCompraTest() throws Exception {
	
		logger.info("---- Se ha invocado el método EntradasCompraTest");
		
		given()
			.port(4444)
			.header("Content-type", "application/json")
			.and()
			.queryParam("mail", "admin@gmail.com")
			.queryParam("password", "1234")
			.queryParam("eventoId", "60e83425fad2c642fd2725d2")
			.queryParam("numTarjeta", 123456)
		.when()
			.post("/entradas/compra")
		.then().log().all()
			.assertThat()
			.statusCode(200);
	}
}
