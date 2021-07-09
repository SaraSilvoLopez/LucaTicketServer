package com.example.spring.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Project LucaTicketEventoService
 *
 * @ClassName Evento
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

public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String nombre;
	private String generoMusical;
	private Recinto recinto;
	private String descripcionCorta;
	private String descripcionExtendida;
	private String foto;
	private LocalDate fecha;
	private LocalTime hora;
	private double rangoPrecios;
	private String politicaAcceso;

}
