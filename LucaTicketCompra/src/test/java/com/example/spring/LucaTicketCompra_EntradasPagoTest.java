package com.example.spring;

import static io.restassured.RestAssured.given;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;

/**
 * @ClassName LucaTicketCompra_EntradasPagoTest
 *
 * @author Patricia García, Usoa Larrarte
 *
 * @date 14 jul. 2021
 * 
 * @version 1.0
 */
@SpringBootConfiguration
public class LucaTicketCompra_EntradasPagoTest {

	private static final Logger logger = Logger.getLogger("LucaTicketCompra_EntradasPagoTest");
	
	@Test
	void entradasPagoTest() throws Exception {
		
		logger.info("---- Se ha invocado el método EntradasPagoTest");
		
		given()
			.port(4444)
			.header("Content-type", "application/json")
			.and()
			.body("{\"nombre\":\"Shakira\", \"apellido\":\"Waka-Waka\",\"numTarjeta\":\"123456\", \"nombreEvento\":\"Uno cualquiera\", \"importe\":\"30\"}")
		.when()
			.post("/entradas/pago")
		.then().log().all()
			.assertThat()
			.statusCode(200);
	}

}
