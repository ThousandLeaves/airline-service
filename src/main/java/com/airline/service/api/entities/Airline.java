package com.airline.service.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="airline")
public class Airline {
	
	@Id
	@Column(name="airlineid")
	private int airlineID;
	@Column(name="airlinename")
	private String airlineName;
	
	public Airline() {
		// Default constructor
	}
	
	public Airline(int airlineID, String airlineName) {
		this.airlineID = airlineID;
		this.airlineName = airlineName;
	}

	public int getAirlineID() {
		return airlineID;
	}

	public void setAirlineID(int airlineID) {
		this.airlineID = airlineID;
	}

	// Getters, setters
	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airline other = (Airline) obj;
		if (airlineID != other.airlineID)
			return false;
		if (airlineName == null) {
			if (other.airlineName != null)
				return false;
		} else if (!airlineName.equals(other.airlineName))
			return false;
		return true;
	}

	// Overrides: toString()
	@Override
	public String toString() {
		return "Airline [airlineID=" + airlineID + ", airlineName=" + airlineName + "]";
	}	

}
