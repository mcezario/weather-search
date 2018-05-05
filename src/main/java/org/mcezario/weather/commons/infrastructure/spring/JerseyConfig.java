package org.mcezario.weather.commons.infrastructure.spring;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.mcezario.weather.commons.resource.AboutResource;
import org.mcezario.weather.commons.resource.IdentifiableExceptionMapper;
import org.mcezario.weather.search.resource.WeatherResource;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(AboutResource.class);
		register(WeatherResource.class);
		register(IdentifiableExceptionMapper.class);
	}

}
