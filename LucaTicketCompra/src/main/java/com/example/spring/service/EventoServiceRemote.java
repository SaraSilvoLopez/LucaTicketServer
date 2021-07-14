package com.example.spring.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.spring.model.Evento;

/**
 * @Project LucaTicketCompra
 *
 * @ClassName EventoServiceRemote
 *
 * @author Usoa Larrarte
 *
 * @date 10 jul. 2021
 * 
 * @version 1.0
 */
@Repository
public class EventoServiceRemote implements EventoService {

private static final Logger logger = Logger.getLogger("");
	
	@Autowired
	protected RestTemplate restTemplate;

	public static final String EVENTOS_SERVICE_URL = "http://EVENTO-MICROSERVICE";
	
	protected String serviceUrl = "http://EVENTO-MICROSERVICE";
	
	public EventoServiceRemote() {
	}

	public EventoServiceRemote(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
		logger.info("-----[CONSTRUCTOR] serviceurl: " + this.serviceUrl);
	}

	@Override
	public Evento getEvento(String id) {
		logger.info("----- [getEvento]: serviceurl: " + this.serviceUrl);
		Evento evento = restTemplate.getForObject(serviceUrl + "/eventos/{id}", Evento.class);
		return evento;
	}

}
