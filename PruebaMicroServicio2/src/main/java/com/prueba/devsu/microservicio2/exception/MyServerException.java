package com.prueba.devsu.microservicio2.exception;

public class MyServerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyServerException() {
		super("Ha ocurrido un error en el servidor");
	}

	public MyServerException(String message) {
		super(message);
	}

	public MyServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyServerException(Throwable cause) {
		super(cause);
	}
}