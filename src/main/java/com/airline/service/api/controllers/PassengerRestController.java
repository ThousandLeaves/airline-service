package com.airline.service.api.controllers;

import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.airline.service.api.entities.Flight;
import com.airline.service.api.entities.Passenger;
import com.airline.service.api.repos.PassengerRepository;
import com.airline.service.api.services.FlightService;
import com.airline.service.api.services.PassengerInfo;
import com.airline.service.api.services.PassengerService;

// Controller returns flights & passengers table as JSON
@RestController
public class PassengerRestController {
	
	@Autowired
	private PassengerRepository passengerRepository;
	@Autowired
	private PassengerService passengerService;
	@Autowired
	private FlightService flightService;
	
	@GetMapping("/api/flights/passenger/{id}")
	public PassengerInfo getPassengerInfo(@PathVariable("id") int idInfo) {
		PassengerInfo passengerInfo = passengerService.getPassengerInfo(idInfo);
		return passengerInfo;
	}
	
	// Make reservation with external site
	@PostMapping("/api/flights/passenger/")
	// Requests JSON with - First name, last name, flight #
	ResponseEntity<Integer> newPassenger(@RequestBody Passenger newPassenger) {
		 HttpHeaders responseHeaders = new HttpHeaders();
		// Attempt to grab existing flight from database, throws exception if not found
		try {
			 Flight flight = flightService.flightToBookById(newPassenger.getFlightNum());
			 int seatNumber = ThreadLocalRandom.current().nextInt(1, flight.getSeatsAvailable() + 1);
			 newPassenger.setSeatNum(String.valueOf(seatNumber));
			 newPassenger.setBookingOrigin(1);
			 passengerRepository.save(newPassenger);
			 
			 //int passengerId = thisPassenger.getPassengerID();
			 Passenger thisPassenger = passengerRepository.isExist(newPassenger.getFirstName(), 
					 newPassenger.getLastName(), newPassenger.getFlightNum());
			 
			 return new ResponseEntity<Integer>(thisPassenger.getPassengerID(), responseHeaders, HttpStatus.OK);
			
		} catch(EntityNotFoundException e) {
			System.out.println(e);
		}
		return new ResponseEntity<Integer>(-1, responseHeaders, HttpStatus.BAD_REQUEST);
	}
	
	// This API endpoint can only be used to remove bookings made from external services.
	// Attempting to remove a booking made internally will fail.
	@DeleteMapping("/api/flights/passenger/{id}")
	public String deletePassenger(@PathVariable("id") int idInfo) {

		Passenger passenger = passengerRepository.findByPassengerID(idInfo);
		passengerRepository.delete(passenger);

		return "Passenger deleted";

		/*Passenger passenger = passengerRepository.findByPassengerID(idInfo);
		
		if(passenger.getBookingOrigin() == 1) {
			passengerRepository.delete(passenger);
			
			return "Passenger deleted";			
		} else {
			System.out.println("Error: You may not delete a booking that did not originate from your service.");
			throw new AccessDeniedException("Access violation occurred. Entity deletion failed.");
		}*/
		
	}

}