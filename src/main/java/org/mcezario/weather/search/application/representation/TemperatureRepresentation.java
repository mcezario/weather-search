package org.mcezario.weather.search.application.representation;

import java.math.BigDecimal;

import org.mcezario.weather.search.gateway.application.domain.model.ClimaTempoData;
import org.mcezario.weather.search.gateway.application.domain.model.Main;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public final class TemperatureRepresentation {

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
	public TemperatureRepresentation(@JsonProperty(TEMPERATURE_ACTUAL) BigDecimal actual,
			@JsonProperty(TEMPERATURE_MIN) BigDecimal min,
			@JsonProperty(TEMPERATURE_MAX) BigDecimal max) {
		this.actual = actual;
		this.min = min;
		this.max = max;
	}

	public static TemperatureRepresentation fromOpenWeatherMap(final Main main) {
		return new TemperatureRepresentation(
				BigDecimal.valueOf(main.getTemp()),
				BigDecimal.valueOf(main.getTemp_min()),
				BigDecimal.valueOf(main.getTemp_max())
				);
	}

	public static TemperatureRepresentation fromClimaTempo(final ClimaTempoData data) {
		return new TemperatureRepresentation(BigDecimal.valueOf(data.getTemperature()), null, null);
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
