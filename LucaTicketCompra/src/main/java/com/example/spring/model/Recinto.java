package com.example.spring.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Project LucaTicketCompra
 *
 * @ClassName Recinto
 *
 * @author María Castro, Patricia García, Usoa Larrarte
 *
 * @date 7 jul. 2021
 * 
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Recinto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nombre;
	private String lugar;
	private Integer aforo;
	private String ciudad;

}
