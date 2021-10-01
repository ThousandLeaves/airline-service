package com.airline.service.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	/**
	 * Tests that assert the behavior of airline application. To do that, you could
	 * start the application and listen for a connection (as it would do in
	 * production) and then send an HTTP request and assert the response.
	 */
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void flightConnectionListenTest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
				.contains("/flight");

	}
	
}
