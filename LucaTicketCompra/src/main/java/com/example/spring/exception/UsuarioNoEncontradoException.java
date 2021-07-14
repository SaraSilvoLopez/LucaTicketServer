package com.example.spring.exception;

public class UsuarioNoEncontradoException extends RuntimeException{
	
	private static final long serialVersionUID = 5L;

	public UsuarioNoEncontradoException() {
		super("No existe el estudiante");
		action1();
	}
	public UsuarioNoEncontradoException(Long id) {
		super("No existe el estudiante "+id);
	}	
	public void action1() {
		
	}
}
