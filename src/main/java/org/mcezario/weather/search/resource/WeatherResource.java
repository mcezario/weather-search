
package org.mcezario.weather.search.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.mcezario.weather.commons.resource.ContentNegotiation;
import org.mcezario.weather.search.application.WeatherService;
import org.mcezario.weather.search.application.representation.WeatherRepresentation;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/weather")
public final class WeatherResource {

	@Autowired
	private WeatherService service;

	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ ContentNegotiation.MOBILE_V1, ContentNegotiation.DESKTOP_V1 })
	@Path("/")
	public WeatherRepresentation weather(@QueryParam("city") String city) {
		return service.findByCity(city);
	}

}
