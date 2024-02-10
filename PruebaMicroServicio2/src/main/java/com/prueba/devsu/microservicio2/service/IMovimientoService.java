package com.prueba.devsu.microservicio2.service;

import org.springframework.http.ResponseEntity;

import com.prueba.devsu.microservicio2.request.MovimientoRequest;

public interface IMovimientoService {

	public ResponseEntity<?> crear(MovimientoRequest request);

	public ResponseEntity<?> listar();

	public ResponseEntity<?> buscarPorId(Integer movimientoId);

	public ResponseEntity<?> actualizar(MovimientoRequest request, Integer movimientoId);

	public ResponseEntity<?> eliminar(Integer movimientoId);

}
