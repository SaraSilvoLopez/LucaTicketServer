package com.example.spring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final UsuarioServiceRemote userEntityService;
		
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		return userEntityService.findByMail(mail)
					.orElseThrow(()-> new UsernameNotFoundException(mail + " no encontrado"));
	}
	
	public UserDetails loadUserById(int id) throws UsernameNotFoundException {
		return userEntityService.findById(id)
				.orElseThrow(()-> new UsernameNotFoundException("Usuario con ID: " + id + " no encontrado"));
	}

}
