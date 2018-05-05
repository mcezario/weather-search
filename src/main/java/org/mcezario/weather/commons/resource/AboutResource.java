package org.mcezario.weather.commons.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.http.ResponseEntity;

@Path("/about")
public final class AboutResource {

	@GET
	@Path("/")
	public ResponseEntity<Void> about() {
		return ResponseEntity.accepted().build();
	}

}
