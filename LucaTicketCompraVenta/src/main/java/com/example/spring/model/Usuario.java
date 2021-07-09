package com.example.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Usuario
 *
 * @author Jennifer Pérez
 *
 * @date 06 jul. 2021
 * 
 * @version 2.0
 */



/**
 * Implementamos Lombok, por lo que con la anotación @Data, quedan incluidos:
 * @Getter, @Setter, @RequiredArgsConstructor, @EqualsAndHashCode y @ToString.
 * @AllArgsConstructor crea el constructor con argumentos, y 
 * @NoArgsConstructor crea el constructor vacío.
 */
@Data @AllArgsConstructor @NoArgsConstructor

public class Usuario {
	
	private int id;
	private String nombre;
	private String apellido;
	private String mail;
	private String contrasenia;
	private String fecha_alta;
	
}
