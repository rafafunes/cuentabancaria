package com.prueba.devsu.microservicio1.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ClienteRequestPost {

	@NotBlank(message = "La identificación es obligatoria")
	@Size(max = 10, message = "La identificación debe tener máximo 10 caracteres")
	private String identificacion;

	@NotBlank(message = "El nombre es obligatorio")
	@Size(max = 150, message = "El nombre debe tener máximo 150 caracteres")
	private String nombre;

	@NotBlank(message = "El género es obligatorio")
	@Size(max = 20, message = "El género debe tener máximo 20 caracteres")
	private String genero;

	@NotNull(message = "La edad es obligatoria")
	@Min(value = 0, message = "La edad debe ser un número positivo")
	@Max(value = 100, message = "La edad debe ser menor o igual a 100")
	private Integer edad;

	@Size(max = 250, message = "La dirección debe tener máximo 250 caracteres")
	private String direccion;

	@Size(max = 20, message = "El teléfono debe tener máximo 20 caracteres")
	private String telefono;

	@NotBlank(message = "La contraseña es obligatoria")
	@Size(max = 50, message = "La contraseña debe tener máximo 50 caracteres")
	private String password;

	@NotNull(message = "El estado es obligatorio")
	private Integer estado;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

}