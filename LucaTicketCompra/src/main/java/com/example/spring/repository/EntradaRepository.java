package com.example.spring.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.model.Entrada;

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

@Repository
public interface EntradaRepository extends MongoRepository<Entrada, String> {
	
	List<Entrada> findByUsuarioId(int usuarioId);

}
