package com.airline.service.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.airline.service.api.repos.AirlineRepository;
import com.airline.service.api.repos.CityRepository;
import com.airline.service.api.repos.FlightRepository;

@SpringBootTest
public class FlightServiceTest {

	@MockBean
	private FlightRepository flightRepository;
	@MockBean 
	private CityRepository cityRepository;
	@MockBean
	private AirlineRepository airlineRepository;
}
