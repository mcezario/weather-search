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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class WeatherService {

	private static final Logger LOGGER = LogManager.getLogger(WeatherService.class);

	@Autowired
	private OpenWeatherMapGatewayService openWeatherMapGatewayService;

	@Autowired
	private ClimatempoGatewayService climatempoGatewayService;

	@Cacheable(value = "weather")
	@HystrixCommand(fallbackMethod = "fallBackOfOpenWeatherMap", commandProperties = {
		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
		@HystrixProperty(name = "execution.timeout.enabled", value = "true")
	})
	public WeatherRepresentation findByCity(String city) {

		LOGGER.debug("[OpenWeatherMap] Find by city={}", city);

		return WeatherRepresentation.fromOpenWeatherMap(openWeatherMapGatewayService.getWeatherByCity(city));

	}

	@HystrixCommand(fallbackMethod = "fallbackOfClimaTempo", commandProperties = {
		@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
		@HystrixProperty(name = "execution.timeout.enabled", value = "true")
	})
	public WeatherRepresentation fallBackOfOpenWeatherMap(String city) {

		LOGGER.debug("[ClimaTempo] Find by city={}", city);

		return WeatherRepresentation.fromClimaTempo(climatempoGatewayService.getWeatherByCity(city));

	}

	public WeatherRepresentation fallbackOfClimaTempo(String city) {
		throw new CityNotFoundException(city);
	}
}
