package com.example.spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project LucaTicketCompra
 *
 * @ClassName Usuario
 *
 * @author Jennifer Pérez
 *
 * @date 06 jul. 2021
 * 
 * @version 2.0
 */

@Data @AllArgsConstructor @NoArgsConstructor

public class Usuario {
	
	private int id;
	private String nombre;
	private String apellido;
	private String mail;
	private String contrasenia;
	private String fecha_alta;
	
}
