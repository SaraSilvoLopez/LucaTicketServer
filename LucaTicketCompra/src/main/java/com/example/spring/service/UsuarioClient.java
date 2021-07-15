package com.example.spring.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.spring.model.Usuario;
import com.example.spring.security.JwtUsuarioRespuesta;
import com.example.spring.security.LoginRequest;

/**
 * @Project LucaTicketCompra
 *
 * @ClassName UsuarioClient
 *
 * @author Usoa Larrarte
 *
 * @date 14 jul. 2021
 * 
 * @version 1.0
 */
@FeignClient(name= "usuarios-microservice")
@RequestMapping
public interface UsuarioClient {
	
	@GetMapping(value = "/usuarios/{id}")
	public Optional<Usuario> findById(@PathVariable ("id") int id);
	
	@GetMapping()
	public Optional<Usuario> findByMail(String Mail);
	
	@PostMapping(value = "/usuarios/login")
	public JwtUsuarioRespuesta login(@Valid @RequestBody LoginRequest loginRequest);

}
