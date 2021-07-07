package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName LucaTicketServerApplication
 *
 * @author María Castro, Patricia García, Usoa Larrarte,
 * Jennifer Pérez y Sara Silvo
 *
 * @date 7 jul. 2021
 * 
 * @version 1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class LucaTicketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LucaTicketServerApplication.class, args);
	}

}
