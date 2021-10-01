package com.airline.service.api.services;

import com.airline.service.api.entities.Flight;
import com.airline.service.api.entities.Passenger;

// Contains information about a passenger and their corresponding flight.
// Integral object for the REST API
public class PassengerInfo {

	public Passenger passenger;
	public Flight flight;
	
	public PassengerInfo() {
		
	}
	
	public PassengerInfo(Passenger passenger, Flight flight) {
		this.passenger = passenger;
		this.flight = flight;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PassengerInfo other = (PassengerInfo) obj;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (passenger == null) {
			if (other.passenger != null)
				return false;
		} else if (!passenger.equals(other.passenger))
			return false;
		return true;
	}

	// To string overrides
	@Override
	public String toString() {
		return "PassengerInfo [passenger=" + passenger + ", flight=" + flight + "]";
	}
	
	
	
}
