package com.prueba.devsu.microservicio2.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;

public class CuentaRequestPut {

	@Size(max = 20, message = "El tipo de cuenta debe tener máximo 20 caracteres")
	private String tipoCuenta;

	@DecimalMin(value = "0.0", inclusive = false, message = "El saldo inicial debe ser mayor que 0")
	@Digits(integer = 10, fraction = 2, message = "El saldo inicial no puede tener más de 10 dígitos en la parte entera y 2 dígitos en la parte decimal")
	private BigDecimal saldoInicial;

	private Integer estado;

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}
