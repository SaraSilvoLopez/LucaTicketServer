package com.example.spring;

import static io.restassured.RestAssured.given;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;

/**
 * @ClassName LucaTicketCompra_EntradasLoginTest
 *
 * @author Patricia García y Usoa Larrarte
 *
 * @date 14 jul. 2021
 * 
 * @version 1.0
 */
@SpringBootConfiguration
public class LucaTicketCompra_EntradasLoginTest {

	private static final Logger logger = Logger.getLogger("LucaTicketCompra_EntradasLoginTest");
	
	@Test
	void entradasLoginTest() throws Exception {
	
		logger.info("---- Se ha invocado el método EntradasLoginTest");

			given()
				.port(4444)
				.header("Content-type", "application/json")
				.and()
				.body("{\"mail\":\"admin@gmail.com\", \"contrasenia\":\"1234\"}")
			.when()
				.post("/entradas/login")
			.then().log().all()
				.assertThat()
				.statusCode(200);
	}
		 
}
