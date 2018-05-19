package org.mcezario.weather.search.gateway.openweathermap.application;


import org.mcezario.weather.search.gateway.commons.domain.model.Weather;
import org.mcezario.weather.search.gateway.openweathermap.domain.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherMapGatewayService {

	@Value("${openweathermap.key}")
	private String appId;

	@Value("${openweathermap.uri}")
	private String uri;

	private final RestTemplate restTemplate;

	public OpenWeatherMapGatewayService(RestTemplate rest) {
		this.restTemplate = rest;
	}

	public Weather getWeatherByCity(final String city) {
		final WeatherResponse response = this.restTemplate.getForObject(uri, WeatherResponse.class, city, appId);
		
		return Weather.fromOpenWeatherMap(response);
	}

}
