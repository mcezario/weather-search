package org.mcezario.weather.search.gateway.commons.application;

import org.mcezario.weather.commons.domain.model.IdentifiableException;

public class CityNotFoundException extends IdentifiableException {

	private static final long serialVersionUID = 3324654108370528278L;

	private static final String MESSAGE_ERROR = "Cidade não encontrada: %s";

	public CityNotFoundException(String city) {
		super(new Id("GWS", 404).withHttpStatusCode(404), String.format(MESSAGE_ERROR, city));
	}

}
