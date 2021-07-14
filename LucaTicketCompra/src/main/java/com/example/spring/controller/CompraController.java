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

import com.example.spring.exception.UsuarioNoEncontradoException;
import com.example.spring.model.Entrada;
import com.example.spring.model.EntradaRespuesta;
import com.example.spring.model.Evento;
import com.example.spring.model.Usuario;
import com.example.spring.repository.EntradaRepository;
import com.example.spring.security.JwtTokenProvider;
import com.example.spring.security.JwtUsuarioRespuesta;
import com.example.spring.security.LoginRequest;
import com.example.spring.security.PagoRequest;
import com.example.spring.service.EventoService;
import com.example.spring.service.UsuarioService;
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
	@Autowired
	private EventoService eventos;
	@Autowired
	private UsuarioService usuarios;

	private static final String LOGIN_USUARIO_URL = "http://localhost:2222/usuarios/login";
	private static final String LOGIN_URL = "http://localhost:6666/entradas/login";
	private static final String PAGO_URL = "http://localhost:8080/pago";
	private static final String REGISTRO_URL = "http://localhost:6666/entradas/registro";

	@PostMapping("/entradas/compra")
	public ResponseEntity<?> compra(@RequestParam("mail") String mail, @RequestParam("password") String password,
			@RequestParam("eventoId") String eventoId, @RequestParam("numTarjeta") int numTarjeta)
			throws JsonProcessingException {

		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<JwtUsuarioRespuesta> loginResponse = null;
		ResponseEntity<?> pagoResponse = null;
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
		if (loginResponse.getStatusCode().equals(HttpStatus.OK)) {

			// llamada a pasarela de pagos
			PagoRequest pagoRequest = new PagoRequest();
			pagoRequest.setNombre(loginResponse.getBody().getNombre());
			pagoRequest.setApellido(loginResponse.getBody().getApellido());
			pagoRequest.setNumTarjeta(numTarjeta);
			pagoRequest.setNombreEvento(eventos.getEvento(eventoId).getNombre());
			pagoRequest.setImporte(eventos.getEvento(eventoId).getRangoPrecios());

			try {
				pagoResponse = pago(pagoRequest);
			} catch (Exception e) {
				return new ResponseEntity<>("No se ha podido realizar el pago", HttpStatus.BAD_REQUEST);
			}

			if (pagoResponse.getStatusCode().equals(HttpStatus.OK)) {

				// registro de la entrada
				Entrada entrada = new Entrada();
				entrada.setUsuarioId(loginResponse.getBody().getId());
				entrada.setEventoId(eventoId);

				try {
					String registroBody = getBody(entrada);
					String token = "Bearer " + loginResponse.getBody().getToken();
					HttpHeaders registroHeaders = getHeaders();
					registroHeaders.set("Authorization", token);
					HttpEntity<String> registroEntity = new HttpEntity<String>(registroBody, registroHeaders);
					registroResponse = restTemplate.postForEntity(REGISTRO_URL, registroEntity, Entrada.class);
					return new ResponseEntity<>("La compra se ha realizado con exito", HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<>("No se ha podido realizar el registro de la entrada",
							HttpStatus.BAD_REQUEST);
				}
			}

		}
		return new ResponseEntity<>("No se ha podido realizar la compra", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/entradas/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws JsonProcessingException {
		RestTemplate restTemplate = new RestTemplate();
		try {
			String loginBody = getBody(loginRequest);
			HttpHeaders loginHeaders = getHeaders();
			HttpEntity<String> loginEntity = new HttpEntity<String>(loginBody, loginHeaders);
			JwtUsuarioRespuesta response = restTemplate.postForObject(LOGIN_USUARIO_URL, loginEntity,
					JwtUsuarioRespuesta.class);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>("No se ha podido realizar el login", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/entradas/pago")
	public ResponseEntity<?> pago(@RequestBody PagoRequest pagoRequest) {
		RestTemplate restTemplate = new RestTemplate();
		try {
			String pagoBody = getBody(pagoRequest);
			HttpHeaders pagoHeaders = getHeaders();
			HttpEntity<String> pagoEntity = new HttpEntity<String>(pagoBody, pagoHeaders);
			String response = restTemplate.postForObject(PAGO_URL, pagoEntity, String.class);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>("No se ha podido realizar el pago", HttpStatus.BAD_REQUEST);
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
	public ResponseEntity<?> misEntradas(@RequestParam("mail") String mail, @RequestParam("password") String password) {
		RestTemplate restTemplate = new RestTemplate();
		// login contra usuarios directamente(mail, password);
		ResponseEntity<JwtUsuarioRespuesta> loginResponse = null;
		LoginRequest loginRequest = new LoginRequest();
		loginRequest.setMail(mail);
		loginRequest.setContrasenia(password);
		try {
			loginResponse = restTemplate.postForEntity(LOGIN_URL, loginRequest, JwtUsuarioRespuesta.class);
		} catch (Exception e) {
			return new ResponseEntity<>("No se ha podido realizar el login", HttpStatus.BAD_REQUEST);
		}
		try {
			int userId = loginResponse.getBody().getId();
			List<Entrada> entradas = new ArrayList();
			entradas = repo.findByUsuarioId(userId);
			List<EntradaRespuesta> entradasRespuesta = new ArrayList();

			for (Entrada e : entradas) {
				String id = e.getId();
				Usuario u = usuarios.findById(e.getUsuarioId()).orElseThrow(UsuarioNoEncontradoException::new);
				Evento ev = eventos.getEvento(e.getEventoId());
				entradasRespuesta.add(new EntradaRespuesta(id, u, ev));
			}
			return new ResponseEntity<>(entradasRespuesta, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("No se ha podido acceder al apartado Mis Entradas", HttpStatus.BAD_REQUEST);
		}
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
