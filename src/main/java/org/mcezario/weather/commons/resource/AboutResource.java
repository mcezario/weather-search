package org.mcezario.weather.commons.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/about")
public final class AboutResource {

	@GET
	@Path("/")
	public Response about() {
		return Response.ok().build();
	}

}
