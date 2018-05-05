package org.mcezario.weather.commons.resource;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mcezario.weather.commons.application.representation.ExceptionRepresentation;
import org.mcezario.weather.commons.domain.model.IdentifiableException;

@Provider
public class IdentifiableExceptionMapper implements ExceptionMapper<IdentifiableException> {

	private static final Logger LOGGER = LogManager.getLogger(IdentifiableExceptionMapper.class);

	@Override
	public Response toResponse(final IdentifiableException e) {
		final Status statusCode = Status.fromStatusCode(e.httpStatusCode());

		if (Status.INTERNAL_SERVER_ERROR == statusCode) {
			LOGGER.error(e.getMessage(), e);

		} else {
			LOGGER.warn("{} ({})", e.getMessage(), e.getClass().getName());
		}

		return ExceptionMapperResponseFactory.create(Status.fromStatusCode(e.httpStatusCode()), new ExceptionRepresentation(e));
	}

}
