package org.mcezario.weather.search.application.representation;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.mcezario.weather.search.gateway.application.domain.model.ClimaTempoData;
import org.mcezario.weather.search.gateway.application.domain.model.ClimaTempoDataResponse;
import org.mcezario.weather.search.gateway.application.domain.model.Main;
import org.mcezario.weather.search.gateway.application.domain.model.Weather;
import org.mcezario.weather.search.gateway.application.domain.model.WeatherResponse;

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
	private final int pressure;

	@JsonProperty(value = HUMIDITY_ATTRIBUTE)
	private final int humidity;

	@JsonProperty(value = TEMPERATURE_ATTRIBUTE)
	private final TemperatureRepresentation temperature;

	@JsonCreator
	public WeatherRepresentation(@JsonProperty(value = DESCRIPTION_ATTRIBUTE) final String description,
			@JsonProperty(value = PRESSURE_ATTRIBUTE) final int pressure,
			@JsonProperty(value = HUMIDITY_ATTRIBUTE) final int humidity,
			@JsonProperty(value = TEMPERATURE_ATTRIBUTE) final TemperatureRepresentation temperature) {
		this.description = description;
		this.pressure = pressure;
		this.humidity = humidity;
		this.temperature = temperature;
	}

	public static WeatherRepresentation fromOpenWeatherMap(final WeatherResponse response) {
		final Main main = response.getMain();
		final Weather weather = response.getWeather().iterator().next();
		final String description = StringUtils.join(weather.getMain(), " - ", weather.getDescription());

		return new WeatherRepresentation(description, main.getPressure(), main.getHumidity(), TemperatureRepresentation.fromOpenWeatherMap(main));
	}

	public static WeatherRepresentation fromClimaTempo(final ClimaTempoDataResponse response) {
		final ClimaTempoData data = response.getData();

		return new WeatherRepresentation(data.getCondition(), data.getPressure(), data.getHumidity(), TemperatureRepresentation.fromClimaTempo(data));
	}

	public final String getDescription() {
		return description;
	}

	public final int getPressure() {
		return pressure;
	}

	public final int getHumidity() {
		return humidity;
	}

	public final TemperatureRepresentation getTemperature() {
		return temperature;
	}

}
