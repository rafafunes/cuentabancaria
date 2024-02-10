package com.prueba.devsu.microservicio2.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MovimientoRequest {

	@NotNull(message = "El valor es obligatorio")
	@Digits(integer = 10, fraction = 2, message = "El valor no puede tener más de 10 dígitos enteros y 2 decimales")
	private BigDecimal valor;

	@NotBlank(message = "El número de cuenta es obligatorio")
	@Size(max = 20, message = "El número de cuenta no puede tener más de 20 caracteres")
	private String numeroCuenta;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

}