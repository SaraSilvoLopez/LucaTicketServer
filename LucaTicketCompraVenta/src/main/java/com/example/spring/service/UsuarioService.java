package com.example.spring.service;

import com.example.spring.model.Usuario;

public interface UsuarioService {
	
	public Usuario getUsuario(int id);
	public Usuario saveUsuario(Usuario usuario);
}
