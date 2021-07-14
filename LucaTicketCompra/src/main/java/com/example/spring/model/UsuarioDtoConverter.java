package com.example.spring.model;

import org.springframework.stereotype.Component;

/**
 * @Project LucaTicketCompra
 *
 * @ClassName UsuarioDtoConverter
 *
 * @author Patricia García y Usoa Larrarte
 *
 * @date 14 jul. 2021
 * 
 * @version 1.0
 */
@Component
public class UsuarioDtoConverter {

	public UsuarioDto convertUserEntityToGetUserDto(Usuario usuario) {
		return UsuarioDto.builder()
				.id(usuario.getId())
				.nombre(usuario.getNombre())
				.apellido(usuario.getApellido())
				.mail(usuario.getMail())
				.contrasenia(usuario.getContrasenia())
				.fecha_alta(usuario.getFecha_alta())			
				.rol(usuario.getRol().toString())
				.build();
	}
}
