package com.example.spring.security;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.example.spring.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.java.Log;

@Log
@Component
public class JwtTokenProvider {

	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE = "JWT";

	@Value("${jwt.secret}")
	private String jwtSecreto;

	@Value("${jwt.token-expiration}")
	private int jwtDuracionTokenEnSegundos;

	public String generateToken(Authentication authentication) {
		
		Usuario usuario = (Usuario) authentication.getPrincipal();
		
		Date tokenExpirationDate = new Date(System.currentTimeMillis() + (jwtDuracionTokenEnSegundos * 1000));
		
		return Jwts.builder()
				.signWith(Keys.hmacShaKeyFor(jwtSecreto.getBytes()), SignatureAlgorithm.HS512)
				.setHeaderParam("typ", TOKEN_TYPE)
				.setSubject(String.valueOf(usuario.getId()))
				.setIssuedAt(new Date())
				.setExpiration(tokenExpirationDate)
				.claim("nombre", usuario.getNombre())
				.claim("apellido", usuario.getApellido())
				.claim("rol", usuario.getRol().toString())
				.compact();

	}

	
	public int getUserIdFromJWT(String token) {
		System.out.println("token1");
		Claims claims = Jwts.parser()
							.setSigningKey(Keys.hmacShaKeyFor(jwtSecreto.getBytes()))
							.parseClaimsJws(token)
							.getBody();
		
		return (int) Integer.parseInt(claims.getSubject());
		
	}
	
	
	public boolean validateToken(String authToken) {
		System.out.println("vali");
		try {
			Jwts.parser().setSigningKey(jwtSecreto.getBytes()).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			log.info("Error en la firma del token JWT: " + ex.getMessage());
		} catch (MalformedJwtException ex) {
			log.info("Token malformado: " + ex.getMessage());
		} catch (ExpiredJwtException ex) {
			log.info("El token ha expirado: " + ex.getMessage());
		} catch (UnsupportedJwtException ex) {
			log.info("Token JWT no soportado: " + ex.getMessage());
		} catch (IllegalArgumentException ex) {
			log.info("JWT claims vacío");
		}
        return false;
		
	}

}
