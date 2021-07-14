package com.example.spring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final AuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAuthorizationFilter jwtAuthorizationFilter;
	private final PasswordEncoder passwordEncoder;
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf()
				.disable()
			.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint) 
			.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/entradas/compra").permitAll()
				.antMatchers(HttpMethod.POST, "/entradas/registro").hasAnyRole("USER","ADMIN")
				//.antMatchers(HttpMethod.GET, "/greeting").hasAnyRole("USER","ADMIN")
				//.antMatchers(HttpMethod.GET, "/greetingAdmin").hasRole("ADMIN")
				//.anyRequest().authenticated();
				.anyRequest().permitAll();

		http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
		
		
	}

}
