package com.prueba.devsu.microservicio2.service;

import java.text.ParseException;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IReporteService {

	public ResponseEntity<?> reporteCuentas(String fechaIni, String fechaFin, Integer cliente)
			throws JsonMappingException, JsonProcessingException, ParseException;

}
