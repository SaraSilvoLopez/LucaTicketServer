package com.example.spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

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
public interface EntradaRepository extends MongoRepository<Entrada, String> {

}
