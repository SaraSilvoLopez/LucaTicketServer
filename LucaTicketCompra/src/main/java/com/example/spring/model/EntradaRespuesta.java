package com.example.spring.model;

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
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class EntradaRespuesta {
	
	private String entradaId;
	private Usuario usuario;
	private Evento evento;
	
}
