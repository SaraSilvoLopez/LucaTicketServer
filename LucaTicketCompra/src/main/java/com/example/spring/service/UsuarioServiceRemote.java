package com.example.spring.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
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
@Repository
public class UsuarioServiceRemote implements UsuarioService {

private static final Logger logger = Logger.getLogger("");
	
	@Autowired
	protected RestTemplate restTemplate;

	protected String serviceUrl;
	
	public UsuarioServiceRemote() {
	}

	public UsuarioServiceRemote(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
		logger.info("-----[CONSTRUCTOR] serviceurl: " + this.serviceUrl);
	}

	@Override
	public Usuario getUsuario(int id) {
		logger.info("----- [getUsuario]: serviceurl: " + this.serviceUrl);
		Usuario usuario = restTemplate.getForObject(serviceUrl + "/usuarios/{id}", Usuario.class);
		return usuario;
	}

}
