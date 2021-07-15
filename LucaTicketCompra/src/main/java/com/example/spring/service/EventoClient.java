package com.example.spring.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring.model.Evento;


/**
 * @Project LucaTicketCompra
 *
 * @ClassName EventoClient
 *
 * @author Usoa Larrarte
 *
 * @date 14 jul. 2021
 * 
 * @version 1.0
 */
@FeignClient(name= "evento-microservice")
@RequestMapping(value="eventos")
public interface EventoClient {
	
	@GetMapping(value = "/{id}")
	public Evento getEvento(@PathVariable String id);
	
}
