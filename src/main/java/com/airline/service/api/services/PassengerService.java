package com.airline.service.api.services;

import com.airline.service.api.exceptions.PassengerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.service.api.entities.Flight;
import com.airline.service.api.entities.Passenger;
import com.airline.service.api.repos.FlightRepository;
import com.airline.service.api.repos.PassengerRepository;

import java.util.ArrayList;
import java.util.List;

// Service calls unique information from database pertaining to an individual's flight details

@Service
public class PassengerService {

	FlightRepository flightRepository;
	PassengerRepository passengerRepository;

	@Autowired
	public PassengerService(FlightRepository flightRepository, PassengerRepository passengerRepository) {
		this.flightRepository = flightRepository;
		this.passengerRepository = passengerRepository;
	}
	
	// Makes calls to database to assemble a passenger and flight object to wrap into 
	// PassengerInfo object.
	public PassengerInfo getPassengerInfo(int id) {
		Passenger currentPassenger = passengerRepository.findByPassengerID(id);
		if (currentPassenger == null) {
			throw new PassengerNotFoundException(id);
		} else {
			Flight currentFlight = flightRepository.findByFlightNum(currentPassenger.getFlightNum());
			return new PassengerInfo(currentPassenger, currentFlight);
		}
	}

	public List<PassengerInfo> getPassengerInfo(String email) {
		List<Passenger> currentPassengerList = passengerRepository.findByPassenger_UserEmail(email);
		if ( currentPassengerList == null || currentPassengerList.isEmpty()) {
			return null;
		}

		List<PassengerInfo> plist = new ArrayList<>();

		for(var cp : currentPassengerList) {
			Flight currentFlight = flightRepository.findByFlightNum(cp.getFlightNum());
			var pi = new PassengerInfo(cp, currentFlight);
			plist.add(pi);
		}

		return plist;
	}

	public void saveFlight(Passenger passenger) {
		passengerRepository.save(passenger);
		
	}

	public Passenger isPresent(String firstName, String lastName, String flightNum) {
		
		return passengerRepository.isExist(firstName,  lastName,  flightNum);
	}
	
}
