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
public class DemasiadosCaracteresException extends RuntimeException {
	private static final long serialVersionUID = 4L;

	/**
	 * Personaliza el mensaje de error cuando el valor introducido supera los 50 caracteres
	 */
	public DemasiadosCaracteresException() {
		super("El tamaño máximo son 50 caracteres");
	}

}
