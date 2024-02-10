package com.prueba.devsu.microservicio2.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.prueba.devsu.microservicio2.exception.MyNotFoundException;
import com.prueba.devsu.microservicio2.exception.MyServerException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
		// se retorno false para no capturar la exception de éste lado
		return false;
	}

	@Override
	public void handleError(ClientHttpResponse httpResponse) throws IOException {
		if (((HttpStatus) httpResponse.getStatusCode()).series() == HttpStatus.Series.SERVER_ERROR) {
			// manejar errores del servidor
			throw new MyServerException();
		} else if (((HttpStatus) httpResponse.getStatusCode()).series() == HttpStatus.Series.CLIENT_ERROR) {
			// manejar errores del cliente
			if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
				throw new MyNotFoundException();
			}
		}

	}
}
