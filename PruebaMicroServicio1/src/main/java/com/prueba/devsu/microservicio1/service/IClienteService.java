package com.prueba.devsu.microservicio1.service;

import org.springframework.http.ResponseEntity;

import com.prueba.devsu.microservicio1.request.ClienteRequestPost;

public interface IClienteService {

	public ResponseEntity<?> crear(ClienteRequestPost request);

	public ResponseEntity<?> findAll();
	
	public ResponseEntity<?> buscarPorId(Integer clienteId);
	
	public ResponseEntity<?> actualizar(Integer clienteId, ClienteRequestPost request);
	
	public ResponseEntity<?> eliminar(Integer clienteId);

}
