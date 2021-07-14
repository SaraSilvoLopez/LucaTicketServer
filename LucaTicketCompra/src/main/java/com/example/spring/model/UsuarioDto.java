package com.example.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Project LucaTicketCompra
 *
 * @ClassName UsuarioDto
 *
 * @author Patricia García y Usoa Larrarte
 *
 * @date 14 jul. 2021
 * 
 * @version 1.0
 */
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class UsuarioDto {
	
	private int id;
	private String nombre;
	private String apellido;
	private String mail;
	private String contrasenia;
	private String fecha_alta;
	private String rol;

}
