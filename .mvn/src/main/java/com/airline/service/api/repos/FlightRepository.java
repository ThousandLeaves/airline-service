package com.airline.service.api.repos;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.airline.service.api.entities.Flight;


@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

	@Query(value="SELECT * FROM flight p WHERE p.leaveDate=?1 and p.arriveDate=?2 and p.seatsAvailable>=?3 and p.originCity=?4 and p.destCity=?5", nativeQuery=true)
	List<Flight> searchFlights(Date leaveDate, Date arriveDate,int seatsAvailable,int originCity,int destCity);
}
