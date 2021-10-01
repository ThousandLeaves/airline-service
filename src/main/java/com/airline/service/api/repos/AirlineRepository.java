package com.airline.service.api.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airline.service.api.entities.Airline;

public interface AirlineRepository extends JpaRepository<Airline, String> {
	
	Airline findByAirlineID(int airlineid);
	
}
