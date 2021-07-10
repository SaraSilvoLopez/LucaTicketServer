package com.example.spring.controller;

import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project LucaTicketPasarelaPagos
 *
 * @ClassName PagosController
 *
 * @author Usoa Larrarte
 *
 * @date 10 jul. 2021
 * 
 * @version 1.0
 */
@RestController
@RequestMapping 
public class PagosController {
	
	private static final Logger logger = Logger.getLogger("");
	
	/**
	 * Método simulado de la pasarela de pagos. Devuelve un respuesta OK si se incluyen los Request Param
	 * de la manera correcta sin verificar su contenido.
	 * 
	 * @param mail
	 * @param password
	 * @param eventoId
	 * @param numTarjeta
	 * @return ResponseEntity.ok()
	 */
	@PostMapping("/")
	public ResponseEntity<?> pago(@RequestParam("mail") String mail, @RequestParam("password") String password, 
			@RequestParam("eventoId") String eventoId, @RequestParam("numTarjeta") String numTarjeta) {
		logger.info("---- Accediendo a la pasarela de pagos");
		return ResponseEntity.ok().build();
	}

	
}
