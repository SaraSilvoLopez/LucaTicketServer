package com.example.spring.model;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDtoConverter {

	public UsuarioDto convertUserEntityToGetUserDto(Usuario usuario) {
		return UsuarioDto.builder()
				.nombre(usuario.getNombre())
				.apellido(usuario.getApellido())
				.mail(usuario.getMail())
				.contrasenia(usuario.getContrasenia())
				.fecha_alta(usuario.getFecha_alta())			
				.rol(usuario.getRol().toString())
				.build();
	}
}
