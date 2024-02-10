package com.prueba.devsu.microservicio2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prueba.devsu.microservicio2.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {
	List<Cuenta> findByClienteId(Integer clienteId);
}