package org.mcezario.weather.search.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mcezario.weather.search.application.representation.WeatherRepresentation;
import org.mcezario.weather.search.gateway.climatempo.application.ClimatempoGatewayService;
import org.mcezario.weather.search.gateway.commons.application.CityNotFoundException;
import org.mcezario.weather.search.gateway.openweathermap.application.OpenWeatherMapGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class WeatherService {

	private static final Logger LOGGER = LogManager.getLogger(WeatherService.class);

	@Autowired
	private OpenWeatherMapGatewayService openWeatherMapGatewayService;

	@Autowired
	private ClimatempoGatewayService climatempoGatewayService;

	@Cacheable(value = "weather")
	@HystrixCommand(fallbackMethod = "fallBackOfOpenWeatherMap")
	public WeatherRepresentation findByCity(final String city) {

		LOGGER.debug("[OpenWeatherMap] Find by city={}", city);

		return new WeatherRepresentation(openWeatherMapGatewayService.getWeatherByCity(city));

	}

	@HystrixCommand(fallbackMethod = "fallbackOfClimaTempo")
	public WeatherRepresentation fallBackOfOpenWeatherMap(final String city) {

		LOGGER.debug("[ClimaTempo] Find by city={}", city);

		return new WeatherRepresentation(climatempoGatewayService.getWeatherByCity(city));

	}

	public WeatherRepresentation fallbackOfClimaTempo(final String city) {

		LOGGER.error("Error to find by city={}", city);

		throw new CityNotFoundException(city);
	}
}
