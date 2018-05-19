package org.mcezario.weather.search.application;

import org.mcezario.weather.commons.domain.model.IdentifiableException;

public final class SearchByCityException extends IdentifiableException {

	private static final long serialVersionUID = 3324654108370528278L;

	private static final String MESSAGE_ERROR = "Temperatura indisponível para a cidade %s";

	public SearchByCityException(String city) {
		super(new Id("SWS", 400).withHttpStatusCode(400), String.format(MESSAGE_ERROR, city));
	}

}
