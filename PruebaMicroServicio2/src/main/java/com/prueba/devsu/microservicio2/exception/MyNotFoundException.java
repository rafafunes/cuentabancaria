package com.prueba.devsu.microservicio2.exception;

public class MyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyNotFoundException() {
		super("El recurso solicitado no se encontró");
	}

	public MyNotFoundException(String message) {
		super(message);
	}

	public MyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyNotFoundException(Throwable cause) {
		super(cause);
	}
}