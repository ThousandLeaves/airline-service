package com.airline.service.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.airline.service.api.controllers.FlightBookingController;
import com.airline.service.api.controllers.FlightController;

@SpringBootTest
public class AirlineServiceAppSmokTest {
	//Sanity Check
	@Autowired
	private FlightController flightController;
	@Autowired
	private FlightBookingController flightBookingController;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(flightController).isNotNull();
		assertThat(flightBookingController).isNotNull();
	}

}
