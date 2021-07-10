package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.spring.service.EventoService;
import com.example.spring.service.EventoServiceRemote;
import com.example.spring.service.UsuarioService;
import com.example.spring.service.UsuarioServiceRemote;

/**
 * @Project LucaTicketCompra
 *
 * @ClassName LucaTicketCompraApplication
 *
 * @author Usoa Larrarte
 *
 * @date 10 jul. 2021
 * 
 * @version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
public class LucaTicketCompraApplication {

	public static final String USUARIOS_SERVICE_URL = "http://USUARIOS-MICROSERVICE";
	public static final String EVENTOS_SERVICE_URL = "http://EVENTO-MICROSERVICE";
	
	public static void main(String[] args) {
		SpringApplication.run(LucaTicketCompraApplication.class, args);
	}
	
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	
	@Bean
	public UsuarioService usuarioService() {
		return new UsuarioServiceRemote(USUARIOS_SERVICE_URL);
	}
	

	@Bean
	public EventoService eventoService() {
		return new EventoServiceRemote(EVENTOS_SERVICE_URL);
	}

}
