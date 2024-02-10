package com.prueba.devsu.microservicio1.util;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Persona {

	@Column(name = "identificacion", nullable = false, length = 10)
	private String identificacion;

	@Column(name = "nombre", nullable = false, length = 150)
	private String nombre;

	@Column(name = "genero", nullable = false, length = 20)
	private String genero;

	@Column(name = "edad", nullable = false)
	private Integer edad;

	@Column(name = "direccion", length = 250)
	private String direccion;

	@Column(name = "telefono", length = 20)
	private String telefono;

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
