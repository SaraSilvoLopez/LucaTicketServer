package com.example.spring.service;

import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.spring.model.Usuario;

/**
 * @Project LucaTicketCompra
 *
 * @ClassName UsuarioServiceRemote
 *
 * @author Usoa Larrarte
 *
 * @date 10 jul. 2021
 * 
 * @version 1.0
 */
@Component
public class UsuarioServiceRemote implements UsuarioService {

private static final Logger logger = Logger.getLogger("");
	
	@Autowired
	protected RestTemplate restTemplate;

	public static final String USUARIOS_SERVICE_URL = "http://USUARIOS-MICROSERVICE";

	protected String serviceUrl = "http://localhost:2222";
	
	public UsuarioServiceRemote() {
	}

	public UsuarioServiceRemote(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
		logger.info("-----[CONSTRUCTOR] serviceurl: " + this.serviceUrl);
	}

	@Override
	public Optional<Usuario> findById(int id) {
		RestTemplate restTemplate = new RestTemplate();
		logger.info("----- [findById]: serviceurl: " + this.serviceUrl +"/"+ id);
		Optional<Usuario> usuario = Optional.of(restTemplate.getForObject(serviceUrl +"/"+ String.valueOf(id), Usuario.class));
		return usuario;
	}
	

	@Override
	public Optional<Usuario> findByMail(String Mail) {
		logger.info("----- [findByMail]: serviceurl: " + this.serviceUrl);
		Optional<Usuario> usuario = Optional.of(restTemplate.getForObject(serviceUrl + "/{mail}", Usuario.class));
		return usuario;
	}

}
