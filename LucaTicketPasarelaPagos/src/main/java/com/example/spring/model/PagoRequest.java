package com.example.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagoRequest {
	
	private String nombre;
	private String apellido;
	private String numTarjeta;
	private String nombreEvento;
	private int importe;
	
}
