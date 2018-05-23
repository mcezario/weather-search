package org.mcezario.weather.search.domain.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.mcezario.weather.search.gateway.climatempo.domain.model.ClimaTempoData;
import org.mcezario.weather.search.gateway.climatempo.domain.model.ClimaTempoDataResponse;
import org.mcezario.weather.search.gateway.openweathermap.domain.model.Main;
import org.mcezario.weather.search.gateway.openweathermap.domain.model.WeatherDescription;
import org.mcezario.weather.search.gateway.openweathermap.domain.model.WeatherResponse;

public final class Weather {

	private final String description;
	private final BigDecimal pressure;
	private final BigDecimal humidity;
	private final Temperature temperature;

	private Weather(final String description,
			final int pressure,
			final int humidity,
			final Temperature temperature) {
		this.description = description;
		this.pressure = new BigDecimal(pressure);
		this.humidity = new BigDecimal(humidity);
		this.temperature = temperature;
	}

	public static Weather fromOpenWeatherMap(final WeatherResponse response) {
		final Main main = response.getMain();
		final WeatherDescription weather = response.getWeather().iterator().next();
		final String description = StringUtils.join(weather.getMain(), " - ", weather.getDescription());

		return new Weather(description, main.getPressure(), main.getHumidity(),
				Temperature.fromOpenWeatherMap(main.getTemp(), main.getTemp_min(), main.getTemp_max()));
	}

	public static Weather fromClimaTempo(final ClimaTempoDataResponse response) {
		final ClimaTempoData data = response.getData();

		return new Weather(data.getCondition(), data.getPressure(), data.getHumidity(), Temperature.fromClimaTempo(data.getTemperature()));
	}

	public String description() {
		return description;
	}

	public BigDecimal pressure() {
		return pressure;
	}

	public BigDecimal humidity() {
		return humidity;
	}

	public Temperature temperature() {
		return temperature;
	}

}
