package com.example.spring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.spring.model.Entrada;
import com.example.spring.model.Evento;

/**
 * @Project LucaTicketCompra
 *
 * @ClassName EntradaRepository
 *
 * @author Usoa Larrarte
 *
 * @date 10 jul. 2021
 * 
 * @version 1.0
 */
public interface EntradaRepository extends MongoRepository<Entrada, String> {
	
	List<Entrada> findByUsuarioId(int usuarioId);

}
