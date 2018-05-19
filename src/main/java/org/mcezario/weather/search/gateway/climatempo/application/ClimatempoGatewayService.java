package org.mcezario.weather.search.gateway.climatempo.application;


import java.util.Arrays;

import org.apache.commons.collections4.ListUtils;
import org.mcezario.weather.search.application.SearchByCityException;
import org.mcezario.weather.search.gateway.climatempo.domain.model.City;
import org.mcezario.weather.search.gateway.climatempo.domain.model.ClimaTempoDataResponse;
import org.mcezario.weather.search.gateway.commons.domain.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClimatempoGatewayService {

	@Value("${advisor.climatempo.key}")
	private String appId;

	@Value("${advisor.climatempo.cities.uri}")
	private String citiesUri;

	@Value("${advisor.climatempo.city.uri}")
	private String cityUri;

	private final RestTemplate restTemplate;

	public ClimatempoGatewayService(RestTemplate rest) {
		this.restTemplate = rest;
	}

	public Weather getWeatherByCity(final String cityName) {
		final City[] cities = this.restTemplate.getForObject(citiesUri, City[].class, cityName, appId);

		final City city = ListUtils.emptyIfNull(Arrays.asList(cities)) //
				.stream() //
				.findFirst() //
				.orElseThrow(() -> new SearchByCityException(cityName));

		final ClimaTempoDataResponse response = this.restTemplate.getForObject(cityUri, ClimaTempoDataResponse.class, city.getId(), appId);
		
		return Weather.fromClimaTempo(response);
	}

}
