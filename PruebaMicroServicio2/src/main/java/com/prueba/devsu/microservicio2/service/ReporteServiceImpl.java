package com.prueba.devsu.microservicio2.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.devsu.microservicio2.model.Cuenta;
import com.prueba.devsu.microservicio2.model.Movimiento;
import com.prueba.devsu.microservicio2.repository.CuentaRepository;
import com.prueba.devsu.microservicio2.repository.MovimientoRepository;
import com.prueba.devsu.microservicio2.util.Cliente;

@Service
public class ReporteServiceImpl implements IReporteService {
	@Autowired
	CuentaRepository cuentaRepository;

	@Autowired
	MovimientoRepository movimientoRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${cliente.service.url}")
	private String clienteServiceUrl;

	ObjectMapper mapper = new ObjectMapper();

	public ResponseEntity<?> reporteCuentas(String fechaIni, String fechaFin, Integer clienteId)
			throws JsonMappingException, JsonProcessingException, ParseException {
		// declarar respuesta
		List<Map<String, Object>> respuesta = new ArrayList<>();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		// se obtiene el cliente
		String uriBuscarCliente = clienteServiceUrl + clienteId;
		ResponseEntity<String> strResponseCliente = restTemplate.getForEntity(uriBuscarCliente, String.class);

		// se valida respuesta exitosa del registerId
		if (strResponseCliente.getStatusCode() != HttpStatus.OK) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El cliente no se pudo recuperar");
		}
		Cliente cliente = mapper.readValue(strResponseCliente.getBody(), Cliente.class);
		// obtener cuentas del cliente
		List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);

		Integer numero = 1;
		for (Cuenta cuenta : cuentas) {
			// obtener lista movimiento de cuentas
			List<Movimiento> movimientos = movimientoRepository.findByCuentaAndFechaBetweenOrderByMovimientoIdAsc(
					cuenta, dateFormat.parse(fechaIni), dateFormat.parse(fechaFin));
			for (Movimiento movimiento : movimientos) {
				Map<String, Object> filaRespuesta = new HashMap<>();
				filaRespuesta.put("Numero", numero);
				filaRespuesta.put("Fecha", movimiento.getFecha());
				filaRespuesta.put("Cliente", cliente.getNombre());
				filaRespuesta.put("Número Cuenta", cuenta.getNumeroCuenta());
				filaRespuesta.put("Tipo", cuenta.getTipoCuenta());
				filaRespuesta.put("Saldo Inicial", movimiento.getSaldo().subtract(movimiento.getValor()));
				filaRespuesta.put("Estado", cuenta.getEstado() == 1 ? true : false);
				filaRespuesta.put("Movimiento", movimiento.getValor());
				filaRespuesta.put("Saldo Disponible", movimiento.getSaldo());

				respuesta.add(filaRespuesta);
				numero++;
			}

		}

		return new ResponseEntity<>(respuesta, HttpStatus.OK);
	}

}
