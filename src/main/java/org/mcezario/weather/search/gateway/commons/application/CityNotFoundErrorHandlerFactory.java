package org.mcezario.weather.search.gateway.commons.application;

import java.io.IOException;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public final class CityNotFoundErrorHandlerFactory implements ResponseErrorHandler {
	
	private final String city;
	
	private CityNotFoundErrorHandlerFactory(String city) {
		this.city = city;
	}
	
	public static CityNotFoundErrorHandlerFactory create(String city) {
		return new CityNotFoundErrorHandlerFactory(city);
	}
	
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return Objects.equals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		throw new CityNotFoundException(city);
	}
	
}
