package com.example.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @Project LucaTicketCompra
 *
 * @ClassName LucaTicketCompraApplication
 *
 * @author Patricia García y Usoa Larrarte
 *
 * @date 10 jul. 2021
 * 
 * @version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LucaTicketCompraApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LucaTicketCompraApplication.class, args);
	}
	
}
