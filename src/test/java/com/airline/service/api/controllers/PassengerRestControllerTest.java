package com.airline.service.api.controllers;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.airline.service.api.entities.Flight;
import com.airline.service.api.entities.Passenger;
import com.airline.service.api.repos.PassengerRepository;
import com.airline.service.api.services.FlightService;
import com.airline.service.api.services.PassengerInfo;
import com.airline.service.api.services.PassengerService;
import com.airline.service.api.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(PassengerRestController.class)
public class PassengerRestControllerTest {

	@MockBean
	PassengerService passengerService;
	
	@MockBean
	FlightService flightService;
	
	@MockBean
	PassengerRepository passengerRepository;
	
	@MockBean
	UserService userService;
	
	@Autowired
	MockMvc mvc;
	
	private JacksonTester<PassengerInfo> jsonPassengerAttempt;
	
	@BeforeEach
	public void setUpEach() {
		MockitoAnnotations.openMocks(this);
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void Test1() throws Exception {
		Passenger passenger = new Passenger(/*99,*/ "Mister", "Tester", "1A", "FL 125", 1);
		Date dateLeave = Date.valueOf("2021-06-10");
		Date dateArrive = Date.valueOf("2021-06-10");
		Time timeLeave = Time.valueOf("12:25:00");
		Time timeArrive = Time.valueOf("1:25:00");
		Flight flight = new Flight("FL 125", 7, timeLeave, dateLeave, timeArrive, dateArrive, 300, 2, 5, 1, 2, "ASD", "FGH");
		
		given(passengerService.getPassengerInfo(99)).willReturn(new PassengerInfo(passenger, flight));
		
		MockHttpServletResponse response = mvc.perform(get("/api/flights/passenger/99")).andReturn().getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		PassengerInfo passengerResult = jsonPassengerAttempt.parseObject(response.getContentAsString());
		PassengerInfo expectedResult = new PassengerInfo(passenger, flight);
		
		assertThat(expectedResult).isEqualTo(passengerResult);
	}
}
