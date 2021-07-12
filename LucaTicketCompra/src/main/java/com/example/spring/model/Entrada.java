package com.example.spring.model;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project LucaTicketCompra
 *
 * @ClassName Entrada
 *
 * @author Usoa Larrarte
 *
 * @date 10 jul. 2021
 * 
 * @version 1.0
 */
@Document(collection = "entradas")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class Entrada {
	
	@MongoId(FieldType.OBJECT_ID)
	private String id;
	@Indexed
	private String usuarioId;
	private String eventoId;
	
}
