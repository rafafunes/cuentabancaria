package com.prueba.devsu.microservicio2.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "movimientos")
public class Movimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movimientoid")
	private Integer movimientoId;

	@Column(name = "fecha", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name = "tipo_movimiento", length = 20)
	private String tipoMovimiento;

	@Column(name = "valor", precision = 10, scale = 2)
	private BigDecimal valor;

	@Column(name = "saldo", precision = 10, scale = 2)
	private BigDecimal saldo;

	@ManyToOne
	@JoinColumn(name = "numero_cuenta", referencedColumnName = "numero_cuenta")
	private Cuenta cuenta;

	public Integer getMovimientoId() {
		return movimientoId;
	}

	public void setMovimientoId(Integer movimientoId) {
		this.movimientoId = movimientoId;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

}