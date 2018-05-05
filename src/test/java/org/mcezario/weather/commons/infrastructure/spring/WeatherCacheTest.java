package org.mcezario.weather.commons.infrastructure.spring;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mcezario.weather.search.application.WeatherService;
import org.mcezario.weather.search.gateway.application.domain.model.Main;
import org.mcezario.weather.search.gateway.application.domain.model.Weather;
import org.mcezario.weather.search.gateway.application.domain.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.github.benmanes.caffeine.cache.Ticker;
import com.google.common.testing.FakeTicker;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherCacheTest {

	@Configuration
    @Import(value = { WeatherApplication.class, JerseyConfig.class, CacheConfig.class })
    public static class TestConfig {

        static FakeTicker fakeTicker = new FakeTicker();

        @Bean
        public Ticker ticker() {
            return fakeTicker::read;
        }

    }

	@SpyBean
	private RestTemplate restTemplate;

	@Autowired
	private WeatherService weatherService;

    @Before
    public void setUp() throws Exception {
    	final WeatherResponse response = new WeatherResponse();
    	response.setName("Name");

    	final Main main = new Main();
    	main.setHumidity(1);
    	main.setPressure(1);
    	main.setTemp(20d);
    	main.setTemp_max(25d);
    	main.setTemp_min(25d);
    	response.setMain(main);

    	final Weather weather = new Weather();
    	weather.setMain("Main");
    	weather.setDescription("Description");
    	response.setWeather(Collections.singletonList(weather));

    	doReturn(response)
    		.when(restTemplate)
    		.getForObject(anyString(), eq(WeatherResponse.class), anyString(), anyString());

    }

    @Test
    public void shouldGuaranteeCacheExpiration() {

    	// 0 minutes
    	weatherService.findByCity("Campinas");
    	verify(restTemplate, times(1)).getForObject(anyString(), eq(WeatherResponse.class), anyString(), anyString());

    	// after 25 minutes
    	TestConfig.fakeTicker.advance(25, TimeUnit.MINUTES);
    	weatherService.findByCity("Campinas");
    	verify(restTemplate, times(1)).getForObject(anyString(), eq(WeatherResponse.class), anyString(), anyString());

    	// after 30 minutes
    	TestConfig.fakeTicker.advance(10, TimeUnit.MINUTES);
    	weatherService.findByCity("Campinas");
    	verify(restTemplate, times(2)).getForObject(anyString(), eq(WeatherResponse.class), anyString(), anyString());

    }


}
