package com.example.spring.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring.model.Evento;


@FeignClient(name= "evento-microservice")
@RequestMapping(value="eventos")
public interface EventoClient {
	
	@GetMapping(value = "/{id}")
	public Evento getEvento(@PathVariable String id);
	
}
