package com.prueba.devsu.microservicio1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.prueba.devsu.microservicio1.model.Cliente;
import com.prueba.devsu.microservicio1.repository.ClienteRepository;
import com.prueba.devsu.microservicio1.request.ClienteRequestPost;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public ResponseEntity<?> crear(ClienteRequestPost request) {
		if (clienteRepository.existsByIdentificacion(request.getIdentificacion())) {
			return new ResponseEntity<>("Un cliente con esta identificación ya existe", HttpStatus.BAD_REQUEST);
		}

		Cliente cliente = new Cliente();
		cliente.setIdentificacion(request.getIdentificacion());
		cliente.setNombre(request.getNombre());
		cliente.setGenero(request.getGenero());
		cliente.setEdad(request.getEdad());
		cliente.setDireccion(request.getDireccion());
		cliente.setTelefono(request.getTelefono());
		cliente.setPassword(request.getPassword());
		cliente.setEstado(request.getEstado());

		clienteRepository.save(cliente);

		return new ResponseEntity<>(cliente, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(clienteRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> buscarPorId(Integer clienteId) {
		Cliente clienteExiste = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente no existe"));
		return new ResponseEntity<>(clienteExiste, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> actualizar(Integer clienteId, ClienteRequestPost request) {
		Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);

		if (!clienteOptional.isPresent()) {
			return new ResponseEntity<>("No se encontró el cliente", HttpStatus.NOT_FOUND);
		}

		Cliente clienteExistente = clienteRepository.findByIdentificacion(request.getIdentificacion());
		if (clienteExistente != null && !clienteExistente.getClienteId().equals(clienteId)) {
			return new ResponseEntity<>("Ya existe un cliente con esta identificación", HttpStatus.BAD_REQUEST);
		}

		Cliente cliente = clienteOptional.get();
		cliente.setNombre(request.getNombre());
		cliente.setGenero(request.getGenero());
		cliente.setEdad(request.getEdad());
		cliente.setDireccion(request.getDireccion());
		cliente.setTelefono(request.getTelefono());
		cliente.setPassword(request.getPassword());
		cliente.setEstado(request.getEstado());

		clienteRepository.save(cliente);

		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> eliminar(Integer clienteId) {
		Cliente clienteExistente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El cliente no existe"));

		clienteRepository.delete(clienteExistente);

		return new ResponseEntity<>("Cliente eliminado", HttpStatus.ACCEPTED);
	}

}
