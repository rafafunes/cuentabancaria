package com.prueba.devsu.microservicio2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.prueba.devsu.microservicio2.request.CuentaRequestPost;
import com.prueba.devsu.microservicio2.request.CuentaRequestPut;
import com.prueba.devsu.microservicio2.service.ICuentaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

	@Autowired
	ICuentaService iCuentaService;

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> crear(@Valid @RequestBody CuentaRequestPost request) {
		try {

			ResponseEntity<?> result = iCuentaService.crear(request);
			return result;

		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listar() {
		try {

			ResponseEntity<?> result = iCuentaService.listar();
			return result;

		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/{numeroCuenta}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buscarPorId(@PathVariable("numeroCuenta") String numeroCuenta) {
		try {
			ResponseEntity<?> result = iCuentaService.buscarPorId(numeroCuenta);
			return result;
		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/{numeroCuenta}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> actualizar(@Valid @RequestBody CuentaRequestPut request,
			@PathVariable("numeroCuenta") String numeroCuenta) {
		try {

			ResponseEntity<?> result = iCuentaService.actualizar(request, numeroCuenta);
			return result;

		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/{numeroCuenta}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> eliminar(@PathVariable("numeroCuenta") String numeroCuenta) {
		try {

			ResponseEntity<?> result = iCuentaService.eliminar(numeroCuenta);
			return result;

		} catch (ResponseStatusException e) {
			return new ResponseEntity<>(e.getReason(), e.getStatusCode());
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
