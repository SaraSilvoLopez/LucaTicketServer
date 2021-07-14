package com.example.spring.security;

import java.time.LocalDateTime;
import java.util.Set;
import com.example.spring.model.UsuarioDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtUsuarioRespuesta extends UsuarioDto {

	private String token;
	
	@Builder(builderMethodName="jwtUsuarioRespuestaBuilder")
	public JwtUsuarioRespuesta(int id, String nombre, String apellido, String mail, String contrasenia, String fecha_alta, String rol, String token) {
		super(id, nombre, apellido, mail, contrasenia, fecha_alta, rol);
		this.token = token;
	}
	
}
