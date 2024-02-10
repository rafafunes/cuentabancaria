package com.prueba.devsu.microservicio2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.prueba.devsu.microservicio2.model.Cuenta;
import com.prueba.devsu.microservicio2.repository.CuentaRepository;
import com.prueba.devsu.microservicio2.request.CuentaRequestPost;
import com.prueba.devsu.microservicio2.request.CuentaRequestPut;

@Service
public class CuentaServiceImpl implements ICuentaService {

	@Autowired
	CuentaRepository cuentaRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${cliente.service.url}")
	private String clienteServiceUrl;

	@Override
	public ResponseEntity<?> crear(CuentaRequestPost request) {
		// validar que el id no esté repetido
		Optional<Cuenta> existeCuenta = cuentaRepository.findById(request.getNumeroCuenta());

		if (existeCuenta.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El número de cuenta ya está en uso");
		}

		// validar que el cliente exista
		String uriBuscarCliente = clienteServiceUrl + request.getClienteId();
		ResponseEntity<String> strResponse = restTemplate.getForEntity(uriBuscarCliente, String.class);

		// se valida respuesta exitosa del registerId
		if (strResponse.getStatusCode() != HttpStatus.OK) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cliente no existe");
		}

		Cuenta cuenta = new Cuenta();
		cuenta.setNumeroCuenta(request.getNumeroCuenta());
		cuenta.setSaldoInicial(request.getSaldoInicial());
		cuenta.setTipoCuenta(request.getTipoCuenta());
		cuenta.setEstado(request.getEstado());
		cuenta.setClienteId(request.getClienteId());

		Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
		return new ResponseEntity<>(cuentaGuardada, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> listar() {
		return new ResponseEntity<>(cuentaRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> buscarPorId(String numeroCuenta) {
		Cuenta cuentaExistente = cuentaRepository.findById(numeroCuenta)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe"));
		return new ResponseEntity<>(cuentaExistente, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> actualizar(CuentaRequestPut request, String numeroCuenta) {
		Cuenta cuentaExistente = cuentaRepository.findById(numeroCuenta)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe"));

		cuentaExistente.setTipoCuenta(request.getTipoCuenta());
		cuentaExistente.setSaldoInicial(request.getSaldoInicial());
		cuentaExistente.setEstado(request.getEstado());

		Cuenta cuentaActualizada = cuentaRepository.save(cuentaExistente);

		return new ResponseEntity<>(cuentaActualizada, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> eliminar(String numeroCuenta) {
		Cuenta cuentaExistente = cuentaRepository.findById(numeroCuenta)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "La cuenta no existe"));
		cuentaRepository.delete(cuentaExistente);

		return new ResponseEntity<>("Cuenta eliminada", HttpStatus.ACCEPTED);

	}

}
