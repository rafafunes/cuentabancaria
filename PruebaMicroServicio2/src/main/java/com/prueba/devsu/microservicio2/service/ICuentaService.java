package com.prueba.devsu.microservicio2.service;

import org.springframework.http.ResponseEntity;

import com.prueba.devsu.microservicio2.request.CuentaRequestPost;
import com.prueba.devsu.microservicio2.request.CuentaRequestPut;

public interface ICuentaService {

	public ResponseEntity<?> crear(CuentaRequestPost request);

	public ResponseEntity<?> listar();

	public ResponseEntity<?> buscarPorId(String numeroCuenta);

	public ResponseEntity<?> actualizar(CuentaRequestPut request, String numeroCuenta);
	
	public ResponseEntity<?> eliminar(String numeroCuenta);

}
