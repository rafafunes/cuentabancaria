package com.prueba.devsu.microservicio1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.devsu.microservicio1.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	boolean existsByIdentificacion(String identificacion);

	Cliente findByIdentificacion(String identificacion);
}