package com.example.spring.service;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring.model.Usuario;

@FeignClient(name= "usuarios-microservice")
@RequestMapping
public interface UsuarioClient {
	
	@GetMapping(value = "/{id}")
	public Optional<Usuario> findById(@PathVariable ("id") int id);
	
	@GetMapping()
	public Optional<Usuario> findByMail(String Mail);

}
