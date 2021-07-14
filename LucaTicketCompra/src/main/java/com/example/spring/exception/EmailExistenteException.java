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

public class EmailExistenteException extends RuntimeException {

	private static final long serialVersionUID = 3L;
	
	/**
	 * Personaliza el mensaje de error cuando el valor introducido en el campo mail
	 * ya existe en la base de datos
	 */
	public EmailExistenteException() {
		super("El email introducido ya se encuentra en la base de datos");
	}
	

}
