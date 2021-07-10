package com.example.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping 
public class PagosController {
	
	
	@PostMapping("/")
	public ResponseEntity<?> pago(@RequestParam("mail") String mail, @RequestParam("password") String password, 
			@RequestParam("eventoId") String eventoId, @RequestParam("numTarjeta") String numTarjeta) {
		return ResponseEntity.ok().build();
	}

	
}
