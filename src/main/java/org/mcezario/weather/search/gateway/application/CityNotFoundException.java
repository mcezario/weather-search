package org.mcezario.weather.search.gateway.application;

import org.mcezario.weather.commons.domain.model.IdentifiableException;

public final class CityNotFoundException extends IdentifiableException {

	private static final String MESSAGE_ERROR = "Temperatura indisponível para a cidade %s";

	public CityNotFoundException(String city) {
		super(new Id("WTS", 400).withHttpStatusCode(400), String.format(MESSAGE_ERROR, city));
	}

}
