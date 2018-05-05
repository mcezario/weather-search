package org.mcezario.weather.commons.infrastructure.spring;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;

@EnableCaching
@Configuration
@Profile("prod")
public class RedisCacheConfig {

	@Value("${cache.weather.expiration}")
	private int weatherExpiration;

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        final RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		final RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setConnectionFactory(connectionFactory);

		return template;
	}

	@Bean
	public CacheManager cacheManager(JedisConnectionFactory jedisConnectionFactory) {

		final RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration //
				.defaultCacheConfig() //
				.entryTtl(Duration.ofMinutes(weatherExpiration)) //
				.prefixKeysWith("weather") //
				.disableCachingNullValues();

		return RedisCacheManager.builder(jedisConnectionFactory) //
				.cacheDefaults(cacheConfiguration) //
				.build();

	}

}
