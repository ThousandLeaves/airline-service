package com.airline.service.api.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airline.service.api.entities.Airline;
import com.airline.service.api.entities.City;
import com.airline.service.api.entities.Flight;

public class FlightInfo {

	private static Logger LOGGER = LoggerFactory.getLogger(FlightInfo.class.getName());
	
	public Flight flight;
	public Airline airline;
	public City originCity;
	public City destCity;

	public FlightInfo() {
		
	}
	
	public FlightInfo(Flight flight, Airline airline, City originCity, City destCity) {
		LOGGER.info("***INITIALIZING FlightInfo object***");
		this.flight = flight;
		this.airline = airline;
		this.originCity = originCity;
		this.destCity = destCity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightInfo other = (FlightInfo) obj;
		if (airline == null) {
			if (other.airline != null)
				return false;
		} else if (!airline.equals(other.airline))
			return false;
		if (destCity == null) {
			if (other.destCity != null)
				return false;
		} else if (!destCity.equals(other.destCity))
			return false;
		if (flight == null) {
			if (other.flight != null)
				return false;
		} else if (!flight.equals(other.flight))
			return false;
		if (originCity == null) {
			if (other.originCity != null)
				return false;
		} else if (!originCity.equals(other.originCity))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FlightInfo [flight=" + flight + ", airline=" + airline + ", originCity=" + originCity + ", destCity="
				+ destCity + "]";
	}
	
}
