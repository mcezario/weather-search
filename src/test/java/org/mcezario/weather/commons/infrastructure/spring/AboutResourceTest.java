package org.mcezario.weather.commons.infrastructure.spring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AboutResourceTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void healthCheck() {

		assertEquals(HttpStatus.OK, restTemplate.getForEntity("/about", Void.class).getStatusCode());
	}

}
