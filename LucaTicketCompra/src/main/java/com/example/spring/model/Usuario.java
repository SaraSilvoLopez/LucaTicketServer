package com.example.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project LucaTicketCompra
 *
 * @ClassName Usuario
 *
 * @author Jennifer Pérez y Sara Silvo
 *
 * @date 7 jul. 2021
 * 
 * @version 1.0
 */

@Data 
@AllArgsConstructor 
@NoArgsConstructor

public class Usuario {
	
	/**
	 * Atributo serialVersionUID
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String nombre;
	private String apellido;
	private String mail;
	private String contrasenia;
	private String fecha_alta;
	private String rol;

}
