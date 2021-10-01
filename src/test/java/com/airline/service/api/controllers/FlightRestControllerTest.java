package com.airline.service.api.controllers;

import java.sql.Date;
import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.mock.web.MockHttpServletResponse;

import com.airline.service.api.configs.SecurityConfiguration;
import com.airline.service.api.entities.Airline;
import com.airline.service.api.entities.City;
import com.airline.service.api.entities.Flight;
import com.airline.service.api.services.FlightInfo;
import com.airline.service.api.services.FlightService;
import com.airline.service.api.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(FlightRestController.class)
public class FlightRestControllerTest {
	
	@MockBean
	FlightService flightService;
	
	@MockBean
	UserService userService;
	
	@Autowired
	private MockMvc mvc;
	
	private JacksonTester<FlightInfo> jsonFlightAttempt;
	
	@BeforeEach
	public void setUpEach() {
		MockitoAnnotations.openMocks(this);
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void test1() throws Exception {
		
		Date dateLeave = Date.valueOf("2021-06-10");
		Date dateArrive = Date.valueOf("2021-06-10");
		Time timeLeave = Time.valueOf("12:25:00");
		Time timeArrive = Time.valueOf("1:25:00");
		Flight flight = new Flight("FL 125", 7, timeLeave, dateLeave, timeArrive, dateArrive, 300, 2, 5, 1, 2, "ASD", "FGH");
		Airline airline = new Airline(10, "Testing Airways");
		City originCity = new City(99, "Test City", "Test Country");
		City destCity = new City(100, "Test City 2", "Test Country 2");
		
		given(flightService.getFlightByIdRest("FL 125")).willReturn(new FlightInfo(flight, airline, originCity, destCity));
		
		MockHttpServletResponse response = mvc.perform(get("/api/flights/flight/FL 125")).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		FlightInfo flightResult = jsonFlightAttempt.parseObject(response.getContentAsString());
		FlightInfo expectedResult = new FlightInfo(flight, airline, originCity, destCity);
		
		assertThat(expectedResult).isEqualTo(flightResult);
	}

}
