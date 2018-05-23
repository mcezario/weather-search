package org.mcezario.weather.search.application.representation;

import java.io.Serializable;
import java.math.BigDecimal;

import org.mcezario.weather.search.domain.model.Temperature;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public final class TemperatureRepresentation implements Serializable {

	private static final long serialVersionUID = 323518934579412599L;

	private static final String TEMPERATURE_ACTUAL = "actual";
	private static final String TEMPERATURE_MIN = "min";
	private static final String TEMPERATURE_MAX = "max";

	@JsonProperty(value = TEMPERATURE_ACTUAL)
	private final BigDecimal actual;

	@JsonProperty(value = TEMPERATURE_MIN)
	private final BigDecimal min;

	@JsonProperty(value = TEMPERATURE_MAX)
	private final BigDecimal max;

	@JsonCreator
	public TemperatureRepresentation(@JsonProperty(TEMPERATURE_ACTUAL) final BigDecimal actual,
			@JsonProperty(TEMPERATURE_MIN) final BigDecimal min,
			@JsonProperty(TEMPERATURE_MAX) final BigDecimal max) {
		this.actual = actual;
		this.min = min;
		this.max = max;
	}

	public TemperatureRepresentation(final Temperature t) {
		this(t.actual(), t.min().orElse(null), t.max().orElse(null));
	}

	public final BigDecimal getActual() {
		return actual;
	}

	public final BigDecimal getMin() {
		return min;
	}

	public final BigDecimal getMax() {
		return max;
	}

}
