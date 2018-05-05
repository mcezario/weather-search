package org.mcezario.weather.commons.resource;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.mcezario.weather.commons.application.representation.ExceptionRepresentation;

public enum ExceptionMapperResponseFactory {

	;

	private static final String ERROR_CODE_HEADER_NAME = "X-Application-Error";

	public static Response create(final Status httpStatusCode, final ExceptionRepresentation rep) {
		return create(httpStatusCode, rep.getCode(), rep);
	}

	public static Response create(final Status httpStatusCode, final String errorCode, final Object rep) {

		return Response
				.status(httpStatusCode)
				.header(ERROR_CODE_HEADER_NAME, errorCode)
				.type(MediaType.APPLICATION_JSON)
				.entity(rep)
				.build();
	}

}
