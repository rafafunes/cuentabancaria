package com.prueba.devsu.microservicio2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.prueba.devsu.microservicio2.service.IReporteService;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
	@Autowired
	IReporteService iReporteService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> reporteCuentas(@RequestParam("fechaIni") String fechaIni,
			@RequestParam("fechaFin") String fechaFin, @RequestParam("cliente") Integer clienteId) {
		try {
			ResponseEntity<?> result = iReporteService.reporteCuentas(fechaIni, fechaFin, clienteId);
			return result;
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
