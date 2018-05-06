package org.mcezario.weather.search.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mcezario.weather.search.application.representation.WeatherRepresentation;
import org.mcezario.weather.search.gateway.application.CityNotFoundException;
import org.mcezario.weather.search.gateway.application.ClimatempoGatewayService;
import org.mcezario.weather.search.gateway.application.OpenWeatherMapGatewayService;
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
	public WeatherRepresentation findByCity(String city) {

		LOGGER.debug("[OpenWeatherMap] Find by city={}", city);

		return WeatherRepresentation.fromOpenWeatherMap(openWeatherMapGatewayService.getWeatherByCity(city));

	}

	@HystrixCommand(fallbackMethod = "fallbackOfClimaTempo")
	public WeatherRepresentation fallBackOfOpenWeatherMap(String city) {

		LOGGER.debug("[ClimaTempo] Find by city={}", city);

		return WeatherRepresentation.fromClimaTempo(climatempoGatewayService.getWeatherByCity(city));

	}

	public WeatherRepresentation fallbackOfClimaTempo(String city) {

		LOGGER.error("Error to find by city={}", city);

		throw new CityNotFoundException(city);
	}
}
