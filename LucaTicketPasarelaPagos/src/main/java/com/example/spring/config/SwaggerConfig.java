package com.example.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Project LucaTicketUsuarioService
 *
 * @ClassName SwaggerConfig
 *
 * @author Usoa Larrarte
 *
 * @date 10 jul. 2021
 * 
 * @version 1.0
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * La libreria Swagger permite autodocumentar los servicios a través de los metadatos. 
	 * El metodo Docket selecciona los path que va a documentar y genera una url que muestra
	 * toda la api documentada con el estandar oatuh.
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

}
