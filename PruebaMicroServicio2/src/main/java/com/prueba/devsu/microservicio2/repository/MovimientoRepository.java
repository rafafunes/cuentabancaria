package com.prueba.devsu.microservicio2.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.devsu.microservicio2.model.Cuenta;
import com.prueba.devsu.microservicio2.model.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
	// recuperar el ultimo movimiento de una cuenta
	public Movimiento findFirstByCuentaOrderByMovimientoIdDesc(Cuenta cuenta);

	public List<Movimiento> findByCuentaAndFechaBetweenOrderByMovimientoIdAsc(Cuenta cuenta, Date fechaInicio,
			Date fechaFin);

}