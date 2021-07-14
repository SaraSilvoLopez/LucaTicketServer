package com.example.spring.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.spring.exception.UsuarioNoEncontradoException;
import com.example.spring.model.Usuario;
import com.example.spring.service.CustomUserDetailsService;
import com.example.spring.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private final JwtTokenProvider tokenProvider;
	private final UsuarioService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println(1);
		try {
			String token = getJwtFromRequest(request);
			System.out.println(2);
			if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
				int userId = tokenProvider.getUserIdFromJWT(token);
				System.out.println(3);
				Usuario usuario = (Usuario) userDetailsService.findById(userId).orElseThrow(UsuarioNoEncontradoException::new);
				List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
				roles.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
				Collection<? extends GrantedAuthority> authorities = roles;
				System.out.println(4);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario,
						usuario.getRol(), authorities);
				authentication.setDetails(new WebAuthenticationDetails(request));
System.out.println(5);
				SecurityContextHolder.getContext().setAuthentication(authentication);

			}
		} catch (Exception ex) {
			System.out.println("error");
			log.info("No se ha podido establecer la autenticación de usuario en el contexto de seguridad");
		}
System.out.println(6);
		filterChain.doFilter(request, response);
System.out.println(7);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		System.out.println("get");
		String bearerToken = request.getHeader(JwtTokenProvider.TOKEN_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtTokenProvider.TOKEN_PREFIX)) {
			System.out.println("getº1");
			return bearerToken.substring(JwtTokenProvider.TOKEN_PREFIX.length(), bearerToken.length());
		}
		return null;
	}

}
