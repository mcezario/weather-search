package org.mcezario.weather.commons.application.representation;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.mcezario.weather.commons.domain.model.IdentifiableException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class ExceptionRepresentation {

	private final String code;
	private final String message;

	@JsonCreator
	protected ExceptionRepresentation(@JsonProperty("code") final String code, @JsonProperty("message") final String message) {
		this.code = code;
		this.message = message;
	}

	public ExceptionRepresentation(final IdentifiableException e) {
		this(e.id(), e.message());
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("code", code).build();
	}

	public final String getCode() {
		return code;
	}

	public final String getMessage() {
		return message;
	}

}
