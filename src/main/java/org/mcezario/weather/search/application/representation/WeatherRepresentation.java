package org.mcezario.weather.search.application.representation;

import java.io.Serializable;
import java.math.BigDecimal;

import org.mcezario.weather.search.domain.model.Weather;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public final class WeatherRepresentation implements Serializable {

	private static final long serialVersionUID = 4915206310231515678L;

	private static final String DESCRIPTION_ATTRIBUTE = "description";
	private static final String PRESSURE_ATTRIBUTE = "pressure";
	private static final String HUMIDITY_ATTRIBUTE = "humidity";
	private static final String TEMPERATURE_ATTRIBUTE = "temperature";

	@JsonProperty(value = DESCRIPTION_ATTRIBUTE)
	private final String description;

	@JsonProperty(value = PRESSURE_ATTRIBUTE)
	private final BigDecimal pressure;

	@JsonProperty(value = HUMIDITY_ATTRIBUTE)
	private final BigDecimal humidity;

	@JsonProperty(value = TEMPERATURE_ATTRIBUTE)
	private final TemperatureRepresentation temperature;

	@JsonCreator
	public WeatherRepresentation(@JsonProperty(value = DESCRIPTION_ATTRIBUTE) final String description,
			@JsonProperty(value = PRESSURE_ATTRIBUTE) final BigDecimal pressure,
			@JsonProperty(value = HUMIDITY_ATTRIBUTE) final BigDecimal humidity,
			@JsonProperty(value = TEMPERATURE_ATTRIBUTE) final TemperatureRepresentation temperature) {
		this.description = description;
		this.pressure = pressure;
		this.humidity = humidity;
		this.temperature = temperature;
	}

	public WeatherRepresentation(final Weather w) {
		this(w.description(), w.pressure(), w.humidity(), new TemperatureRepresentation(w.temperature()));
	}

	public final String getDescription() {
		return description;
	}

	public final BigDecimal getPressure() {
		return pressure;
	}

	public final BigDecimal getHumidity() {
		return humidity;
	}

	public final TemperatureRepresentation getTemperature() {
		return temperature;
	}

}
