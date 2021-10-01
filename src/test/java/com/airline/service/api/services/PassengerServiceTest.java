package com.airline.service.api.services;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.airline.service.api.entities.Flight;
import com.airline.service.api.entities.Passenger;
import com.airline.service.api.repos.FlightRepository;
import com.airline.service.api.repos.PassengerRepository;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PassengerServiceTest {

	@MockBean
	FlightRepository flightRepository;
	@MockBean 
	PassengerRepository passengerRepository;
	
	private PassengerService passengerService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void getPassengerInfoTest() {
		Passenger passenger = new Passenger("Test", "Passenger", "44", "AA720", 2);
		passenger.setPassengerID(1);
		Time leaveTime = Time.valueOf(LocalTime.now());
		Time arriveTime = Time.valueOf(LocalTime.now());
		Date arriveDate = Date.valueOf(LocalDate.now());
		Flight flight = new Flight("AA720", 10, leaveTime, arriveDate, arriveTime, arriveDate, 300, 1, 20, 50, 51, "APA", "APB");
		
		given(flightRepository.findByFlightNum("AA720")).willReturn(flight);
		given(passengerRepository.findByPassengerID(1)).willReturn(passenger);
		
		passengerService = new PassengerService(flightRepository, passengerRepository);
		PassengerInfo expectedResult = new PassengerInfo(passenger, flight);
		
		assertEquals(expectedResult,passengerService.getPassengerInfo(passenger.getPassengerID()));
	}
	
}
