package org.mcezario.weather.commons.infrastructure.spring;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Ticker;

@EnableCaching
@Configuration
public class CacheConfig {

	@Value("${cache.weather.expiration}")
	private int weatherExpiration;

	@Bean
	public CacheManager cacheManager(Ticker ticker) {
	    final CaffeineCache weatherCache = buildCache("weather", ticker, weatherExpiration);

	    final SimpleCacheManager manager = new SimpleCacheManager();
	    manager.setCaches(Arrays.asList(weatherCache));

	    return manager;
	}

	private CaffeineCache buildCache(String name, Ticker ticker, int minutesToExpire) {
	    return new CaffeineCache(name, Caffeine.newBuilder()
	                .expireAfterWrite(minutesToExpire, TimeUnit.MINUTES)
	                .maximumSize(100)
	                .ticker(ticker)
	                .build());
	}

	@Bean
	public Ticker ticker() {
	    return Ticker.systemTicker();
	}

}
