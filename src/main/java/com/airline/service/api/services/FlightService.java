package com.airline.service.api.services;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.service.api.entities.Airline;
import com.airline.service.api.entities.City;
import com.airline.service.api.entities.Flight;
import com.airline.service.api.exceptions.FlightNotFoundException;
import com.airline.service.api.repos.AirlineRepository;
import com.airline.service.api.repos.CityRepository;
import com.airline.service.api.repos.FlightRepository;

@Service
public class FlightService {

	private static Logger LOGGER = LoggerFactory.getLogger(FlightService.class.getName());

	private final FlightRepository flightRepository;
	private final CityRepository cityRepository;
	private final AirlineRepository airlineRepository;

	@Autowired
	public FlightService(FlightRepository flightRepository, CityRepository cityRepository, AirlineRepository airlineRepository) {
		this.flightRepository = flightRepository;
		this.cityRepository = cityRepository;
		this.airlineRepository = airlineRepository;
	}

	public Flight getFlightById(String id) {
		Flight flight = flightRepository.getById(id);
		// Probably can use an optional here
		if (flight == null) {
			throw new FlightNotFoundException(id);
		} else {
			return flight;
		}
	}
	
	// instance of getFlight to return flightInfo object instead of flight object
	public FlightInfo getFlightByIdRest(String id) {
		Flight flight = flightRepository.getById(id);
		if (flight == null) {
			throw new FlightNotFoundException(id);
		} else {
			// Packaged objects for REST API
			Airline airline = airlineRepository.findByAirlineID(flight.getAirlineID());
			City destCity = cityRepository.findByCityID(flight.getDestCity());
			City originCity = cityRepository.findByCityID(flight.getOriginCity());
			return new FlightInfo(flight, airline, originCity, destCity);
		}

	}

	public List<Flight> getAllFlights() {
		List<Flight> flights = flightRepository.findAll();
		if (flights == null || flights.size() == 0) {
			LOGGER.info("***No flights***");
			throw new FlightNotFoundException();
		} else {
			return flights;
		}

	}

	public void saveFlightToDB(Flight flight) {
		if (flight != null) {
			flightRepository.save(flight);
		} else {
			throw new RuntimeException("No flight records to save");
		}
	}

	public List<Flight> searchFlightRecords(Flight flight, String flightType) {

		Date leaveDate=flight.getLeaveDate(), arriveDate=flight.getArriveDate();
		String leaveDate1 = getSt(leaveDate);
		String arriveDate1 = getSt(arriveDate);

		int seatsAvailable=flight.getSeatsAvailable(), originCity=flight.getOriginCity(), destCity=flight.getDestCity();
		List<Flight> flightResult = new ArrayList<>();
		if(flightType!=null) {
			int price=Integer.parseInt(flightType);
			List<Flight> all = flightRepository.findAll();
			for(var fl : all) {

				String leaveDate2 = getSt(fl.getLeaveDate());
				String arriveDate2 = getSt(fl.getArriveDate());

				if (!leaveDate2.equals(leaveDate1)) continue;
				if (!arriveDate2.equals(arriveDate1)) continue;
				if ( fl.getSeatsAvailable() < seatsAvailable) continue;
				if ( fl.getOriginCity() != originCity) continue;
				if ( fl.getDestCity() != destCity) continue;
				flightResult.add(fl);
			}

			 for (Flight temp : flightResult) {
				 temp.setPrice(temp.getPrice()*price);
		            
		        }
		}
		return flightResult; 
	}

	public Flight flightToBookById(String id)
	{
		return flightRepository.getById(id);
	}

	private String getSt(Date date) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String strDate = simpleDateFormat.format(date);
		return strDate;
	}
}
