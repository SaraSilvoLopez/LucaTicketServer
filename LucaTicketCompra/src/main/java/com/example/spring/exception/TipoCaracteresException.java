package com.example.spring.exception;

/**
 * @Project LucaTicketUsuarioService
 *
 * @ClassName LucaTicketUsuarioServiceApplication
 *
 * @author Jennifer Pérez y Sara Silvo
 *
 * @date 7 jul. 2021
 * 
 * @version 1.0
 */

public class TipoCaracteresException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	/**
	 * Personaliza el mensaje de error cuando el valor introducido en el campo 
	 * nombre y apellidos contiene un valor diferente a una letra
	 */
	
	public TipoCaracteresException() {
		super("Los campos 'nombre' y ' apellido' solo pueden contener letras");
	}
}