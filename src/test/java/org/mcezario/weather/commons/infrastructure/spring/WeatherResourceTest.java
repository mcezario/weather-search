package org.mcezario.weather.commons.infrastructure.spring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mcezario.weather.commons.application.representation.ExceptionRepresentation;
import org.mcezario.weather.search.application.representation.WeatherRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherResourceTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldGetWeatherOfOpenWeatherMapWithoutFallback() {

		final ResponseEntity<WeatherRepresentation> response = restTemplate.getForEntity("/weather?city=Campinas", WeatherRepresentation.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Clouds - nuvens dispersas", response.getBody().getDescription());
		assertEquals(30, response.getBody().getHumidity());
		assertEquals(1018, response.getBody().getPressure());
		assertEquals(new BigDecimal("28.0"), response.getBody().getTemperature().getActual());
		assertEquals(new BigDecimal("20.0"), response.getBody().getTemperature().getMin());
		assertEquals(new BigDecimal("29.0"), response.getBody().getTemperature().getMax());
	}

	@Test
	public void shouldExecuteFallbackGettingWeatherOfClimaTempo() {

		final ResponseEntity<WeatherRepresentation> response = restTemplate.getForEntity("/weather?city=Valinhos", WeatherRepresentation.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Alguma nebulosidade", response.getBody().getDescription());
		assertEquals(34, response.getBody().getHumidity());
		assertEquals(1018, response.getBody().getPressure());
		assertEquals(new BigDecimal("27"), response.getBody().getTemperature().getActual());
		assertNull(response.getBody().getTemperature().getMin());
		assertNull(response.getBody().getTemperature().getMax());
	}

	@Test
	public void shouldGetBadRequestIfBothFail() {

		final ResponseEntity<ExceptionRepresentation> response = restTemplate.getForEntity("/weather?city=Vinhedo", ExceptionRepresentation.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("WTS-0400", response.getBody().getCode());
		assertEquals("Temperatura indisponível para a cidade Vinhedo", response.getBody().getMessage());
	}

}
