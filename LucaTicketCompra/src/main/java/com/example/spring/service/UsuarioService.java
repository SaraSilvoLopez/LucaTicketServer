package com.example.spring.service;

import java.util.Optional;

import com.example.spring.model.Usuario;

/**
 * @Project LucaTicketCompra
 *
 * @ClassName UsuarioService
 *
 * @author Usoa Larrarte
 *
 * @date 10 jul. 2021
 * 
 * @version 1.0
 */
public interface UsuarioService {
	
	public Usuario getUsuario(int id);
	
	public Optional<Usuario> findById(int id);
	
	public Optional<Usuario> findByMail(String Mail);
}
