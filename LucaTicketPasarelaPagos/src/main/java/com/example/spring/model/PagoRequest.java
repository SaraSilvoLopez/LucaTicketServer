package com.example.spring.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PagoRequest {
	
	private String nombre;
	private String apellido;
	private String numTarjeta;
	private String nombreEvento;
	private double importe;
	
}
