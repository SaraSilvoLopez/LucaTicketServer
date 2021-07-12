package com.example.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.spring.model.Entrada;
import com.example.spring.repository.EntradaRepository;
import com.example.spring.security.JwtUsuarioRespuesta;
import com.example.spring.security.LoginRequest;
import com.example.spring.security.PagoRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	private static final String LOGIN_USUARIO_URL = "http://localhost:2222/usuarios/login";
	private static final String LOGIN_URL = "http://localhost:6666/entradas/login";
	private static final String PAGO_URL = "http://localhost:8080/pago";
	private static final String REGISTRO_URL = "http://localhost:6666/entradas/registro";

	@PostMapping("/entradas/compra")
	public ResponseEntity<?> compra(@RequestParam("mail") String mail, @RequestParam("password") String password,
			@RequestParam("eventoId") String eventoId, @RequestParam("numTarjeta") int numTarjeta) throws JsonProcessingException {

		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<JwtUsuarioRespuesta> loginResponse = null;
		String pagoResponse = null;
		ResponseEntity<?> registroResponse = null;

		// llamada a usuarios para el login
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setMail(mail);
		loginRequest.setContrasenia(password);
		
		try {
			loginResponse = restTemplate.postForEntity(LOGIN_URL, loginRequest, JwtUsuarioRespuesta.class);
		} catch (Exception e) {
			return new ResponseEntity<>("No se ha podido realizar el login", HttpStatus.BAD_REQUEST);
		}
		//if (loginResponse.getStatusCode().equals(HttpStatus.OK)) {
			PagoRequest pagoRequest = new PagoRequest();
			pagoRequest.builder().nombre(loginResponse.getBody().getNombre())
					.apellido(loginResponse.getBody().getApellido()).numTarjeta(numTarjeta)
					.nombreEvento("evento comprado en LucaTicket").importe(0).build();
			try {
				pagoResponse = pago(pagoRequest);
			} catch (Exception e) {
				return new ResponseEntity<>("No se ha podido realizar el pago", HttpStatus.BAD_REQUEST);
			}

			if (pagoResponse.equals("Pago aceptado")) {
				Entrada entrada = new Entrada();
				entrada.builder().usuarioId(loginResponse.getBody().getMail()).eventoId(eventoId).build();
				try {
					registroResponse = addEntrada(entrada);
					return new ResponseEntity<>("La compra se ha realizado con exito",HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<>("No se ha podido realizar el registro de la entrada",
							HttpStatus.BAD_REQUEST);
				}
			//}
	
	}
		return new ResponseEntity<>("No se ha podido realizar la compra", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/entradas/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest)
			throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		try {
			String loginBody = getBody(loginRequest);
			HttpHeaders loginHeaders = getHeaders();
			HttpEntity<String> loginEntity = new HttpEntity<String>(loginBody, loginHeaders);
			JwtUsuarioRespuesta response = restTemplate.postForObject(LOGIN_USUARIO_URL, loginEntity, JwtUsuarioRespuesta.class);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>("No se ha podido realizar el login", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/entradas/pago")
	public String pago(@RequestBody PagoRequest pagoRequest) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			String pagoBody = getBody(pagoRequest);
			HttpHeaders pagoHeaders = getHeaders();
			HttpEntity<String> pagoEntity = new HttpEntity<String>(pagoBody, pagoHeaders);
			return restTemplate.postForObject(PAGO_URL, pagoEntity, String.class);
		} catch (Exception ex) {
			return "No se ha podido realizar el pago";
		}
	}

	@PostMapping("/entradas/registro")
	public ResponseEntity<?> addEntrada(@RequestBody Entrada entrada) {
		try {
			repo.save(entrada);
			return ResponseEntity.ok(entrada);
		} catch (Exception ex) {
			return new ResponseEntity<>("No se ha podido realizar el registro de la entrada", HttpStatus.BAD_REQUEST);
		}
	}

	
	  @PostMapping("/entradas/misentradas") 
	  public List<Entrada> misEntradas(@RequestParam("mail") String mail, @RequestParam("password") String password) { 
	  //login contra usuarios directamente(mail, password);
	  //conseguir usuarioId 
		  List<Entrada> entradas= new ArrayList(); 
	  //entradas =
	  //EntradasRepo.findByUsuarioId(); 
	  return entradas; 
	  }
	 

	private <T> String getBody(T request) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(request);
	}

	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
}
