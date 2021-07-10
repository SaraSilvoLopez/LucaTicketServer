package com.example.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.model.Entrada;
import com.example.spring.repository.EntradaRepository;


/**
 * @Project LucaTicketCompra
 *
 * @ClassName CompraController
 *
 * @author Usoa Larrarte
 *
 * @date 10 jul. 2021
 * 
 * @version 1.0
 */
@RestController
@RequestMapping 
public class CompraController {
	
	private static final Logger logger = Logger.getLogger("");
	
	@Autowired
	private EntradaRepository repo;
	
	@PostMapping("/entradas/compra")
	public String compra(@RequestParam("mail") String mail, @RequestParam("password") String password, 
			@RequestParam("eventoId") String eventoId, @RequestParam("numTarjeta") String numTarjeta) {
		//login contra usuarios directamente(mail, password);
		//llamada pasarela de pagos
		//guardar entrada -> conseguir usuarioId del token
		return null;	
	}
	
	@PostMapping("/entradas/registrarentrada")
	public ResponseEntity<?> addEntrada(@RequestBody Entrada entrada) {
		repo.save(entrada);
		return null;	
	}
	
	
	@PostMapping("/entradas/misentradas")
	public List<Entrada> misEntradas(@RequestParam("mail") String mail, @RequestParam("password") String password) {
		//login contra usuarios directamente(mail, password);
		//conseguir usuarioId
		List<Entrada> entradas= new ArrayList();
		//entradas = EntradasRepo.findByUsuarioId();
		return entradas;	
	}
	
	/*
	@PostMapping("/entradas/login")
	public String login(@RequestParam("mail") String mail, @RequestParam("password") String password) {
		String token = null;
		if(authentication(mail, password)) {
			token = getJWTToken();	
		}
		return token;	
	}
	
	private boolean authentication(String mail, String password) {
		try {
		Usuario usuario = serv.getUsuario(1);
		if(usuario.getContrasenia()==password) {
			return true;
		} else {
			return false;
		}
		} catch (Exception e) {
			//Usuario no existe
			return false;
		}
		
	}
	*/
}
