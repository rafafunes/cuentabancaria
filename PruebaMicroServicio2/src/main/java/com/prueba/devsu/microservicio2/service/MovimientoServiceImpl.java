package com.prueba.devsu.microservicio2.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.prueba.devsu.microservicio2.model.Cuenta;
import com.prueba.devsu.microservicio2.model.Movimiento;
import com.prueba.devsu.microservicio2.repository.MovimientoRepository;
import com.prueba.devsu.microservicio2.request.MovimientoRequest;

@Service
public class MovimientoServiceImpl implements IMovimientoService {

	@Autowired
	MovimientoRepository movimientoRepository;

	@Autowired
	ICuentaService iCuentaService;

	@Override
	public ResponseEntity<?> crear(MovimientoRequest request) {

		BigDecimal saldoActual;

		ResponseEntity<?> responseCuenta = iCuentaService.buscarPorId(request.getNumeroCuenta());
		Cuenta cuenta = (Cuenta) responseCuenta.getBody();

		Movimiento movimiento = new Movimiento();
		movimiento.setFecha(new Date());
		movimiento.setTipoMovimiento(request.getValor().compareTo(new BigDecimal(0)) < 0 ? "Retiro" : "Depósito");
		movimiento.setValor(request.getValor());
		movimiento.setCuenta(cuenta);

		// recuperar el ultimo movimiento de esa cuenta
		Movimiento ultimoMovimiento = movimientoRepository.findFirstByCuentaOrderByMovimientoIdDesc(cuenta);
		if (ultimoMovimiento != null) {
			saldoActual = ultimoMovimiento.getSaldo();
			movimiento.setSaldo(ultimoMovimiento.getSaldo().add(request.getValor()));
		} else {
			saldoActual = cuenta.getSaldoInicial();
			movimiento.setSaldo(cuenta.getSaldoInicial().add(request.getValor()));
		}

		// validar que el valor restante no sea menos que cero
		if (movimiento.getSaldo().compareTo(new BigDecimal(0)) < 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Saldo es insuficiente, su saldo es: " + saldoActual);
		}

		Movimiento nuevoMovimiento = movimientoRepository.save(movimiento);
		return new ResponseEntity<>(nuevoMovimiento, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> listar() {
		return new ResponseEntity<>(movimientoRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> buscarPorId(Integer movimientoId) {
		Movimiento movimientoExistente = movimientoRepository.findById(movimientoId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El movimiento no existe"));
		return new ResponseEntity<>(movimientoExistente, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> actualizar(MovimientoRequest request, Integer movimientoId) {
		Movimiento movimientoExistente = movimientoRepository.findById(movimientoId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El movimiento no existe"));
		/* la fecha del movimiento no se actualiza */

		// movimientoExistente.setTipoMovimiento(request.getTipoMovimiento());
		movimientoExistente.setValor(request.getValor());
		// movimientoExistente.setSaldo(request.getSaldo());

		ResponseEntity<?> responseCuenta = iCuentaService.buscarPorId(request.getNumeroCuenta());
		Cuenta cuenta = (Cuenta) responseCuenta.getBody();

		movimientoExistente.setCuenta(cuenta);

		Movimiento movimientoActualizado = movimientoRepository.save(movimientoExistente);

		return new ResponseEntity<>(movimientoActualizado, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> eliminar(Integer movimientoId) {
		Movimiento movimientoExistente = movimientoRepository.findById(movimientoId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El movimiento no existe"));

		movimientoRepository.delete(movimientoExistente);

		return new ResponseEntity<>("Movimiento eliminado", HttpStatus.ACCEPTED);
	}

}
