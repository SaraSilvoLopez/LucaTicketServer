package com.example.spring.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
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
@Data @AllArgsConstructor @NoArgsConstructor

public class Entrada {
	
	@MongoId(FieldType.OBJECT_ID)
	private int id;
	@Indexed
	private Usuario usuario;
	private Evento evento;
	
}
