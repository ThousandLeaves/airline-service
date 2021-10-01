package com.airline.service.api.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flight")
public class Flight implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flightnum" ,nullable = false, unique = true)
	private String flightNum;
	@Column(name = "airlineid")
	private int airlineID;
	@Column(name = "leavetime")
	private Time leaveTime;
	@Column(name = "leavedate")
	private Date leaveDate;
	@Column(name = "arrivetime")
	private Time arriveTime;
	@Column(name = "arrivedate")
	private Date arriveDate;
	@Column(name = "price")
	private int price;
	@Column(name = "numstops")
	private int numStops;
	@Column(name = "seatsavailable")
	private int seatsAvailable;
	@Column(name = "origincity")
	private int originCity;
	@Column(name = "destcity")
	private int destCity;
	@Column(name = "originairport")
	private String originAirport;
	@Column(name = "destairport")
	private String destAirport;

	public Flight() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param flightNum
	 * @param airlineID
	 * @param leaveTime
	 * @param leaveDate
	 * @param arriveTime
	 * @param arriveDate
	 * @param price
	 * @param numStops
	 * @param seatsAvailable
	 * @param originCity
	 * @param destCity
	 * @param originAirport
	 * @param destAirport
	 */
	public Flight(String flightNum, int airlineID, Time leaveTime, Date leaveDate, Time arriveTime, Date arriveDate,
			int price, int numStops, int seatsAvailable, int originCity, int destCity, String originAirport,
			String destAirport) {
		super();
		this.flightNum = flightNum;
		this.airlineID = airlineID;
		this.leaveTime = leaveTime;
		this.leaveDate = leaveDate;
		this.arriveTime = arriveTime;
		this.arriveDate = arriveDate;
		this.price = price;
		this.numStops = numStops;
		this.seatsAvailable = seatsAvailable;
		this.originCity = originCity;
		this.destCity = destCity;
		this.originAirport = originAirport;
		this.destAirport = destAirport;
	}

	/**
	 * @return the flightNum
	 */
	public String getFlightNum() {
		return flightNum;
	}

	/**
	 * @param flightNum the flightNum to set
	 */
	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	/**
	 * @return the airlineID
	 */
	public int getAirlineID() {
		return airlineID;
	}

	/**
	 * @param airlineID the airlineID to set
	 */
	public void setAirlineID(int airlineID) {
		this.airlineID = airlineID;
	}

	/**
	 * @return the leaveTime
	 */
	public Time getLeaveTime() {
		return leaveTime;
	}

	/**
	 * @param leaveTime the leaveTime to set
	 */
	public void setLeaveTime(Time leaveTime) {
		this.leaveTime = leaveTime;
	}

	/**
	 * @return the leaveDate
	 */
	public Date getLeaveDate() {
		return leaveDate;
	}

	/**
	 * @param leaveDate the leaveDate to set
	 */
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	/**
	 * @return the arriveTime
	 */
	public Time getArriveTime() {
		return arriveTime;
	}

	/**
	 * @param arriveTime the arriveTime to set
	 */
	public void setArriveTime(Time arriveTime) {
		this.arriveTime = arriveTime;
	}

	/**
	 * @return the arriveDate
	 */
	public Date getArriveDate() {
		return arriveDate;
	}

	/**
	 * @param arriveDate the arriveDate to set
	 */
	public void setArriveDate(Date arriveDate) {
		this.arriveDate = arriveDate;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the numStops
	 */
	public int getNumStops() {
		return numStops;
	}

	/**
	 * @param numStops the numStops to set
	 */
	public void setNumStops(int numStops) {
		this.numStops = numStops;
	}

	/**
	 * @return the seatsAvailable
	 */
	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	/**
	 * @param seatsAvailable the seatsAvailable to set
	 */
	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	/**
	 * @return the originCity
	 */
	public int getOriginCity() {
		return originCity;
	}

	/**
	 * @param originCity the originCity to set
	 */
	public void setOriginCity(int originCity) {
		this.originCity = originCity;
	}

	/**
	 * @return the destCity
	 */
	public int getDestCity() {
		return destCity;
	}

	/**
	 * @param destCity the destCity to set
	 */
	public void setDestCity(int destCity) {
		this.destCity = destCity;
	}

	/**
	 * @return the originAirport
	 */
	public String getOriginAirport() {
		return originAirport;
	}

	/**
	 * @param originAirport the originAirport to set
	 */
	public void setOriginAirport(String originAirport) {
		this.originAirport = originAirport;
	}

	/**
	 * @return the destAirport
	 */
	public String getDestAirport() {
		return destAirport;
	}

	/**
	 * @param destAirport the destAirport to set
	 */
	public void setDestAirport(String destAirport) {
		this.destAirport = destAirport;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Flight other = (Flight) obj;
		if (airlineID != other.airlineID)
			return false;
		if (arriveDate == null) {
			if (other.arriveDate != null)
				return false;
		} else if (!arriveDate.equals(other.arriveDate))
			return false;
		if (arriveTime == null) {
			if (other.arriveTime != null)
				return false;
		} else if (!arriveTime.equals(other.arriveTime))
			return false;
		if (destAirport == null) {
			if (other.destAirport != null)
				return false;
		} else if (!destAirport.equals(other.destAirport))
			return false;
		if (destCity != other.destCity)
			return false;
		if (flightNum == null) {
			if (other.flightNum != null)
				return false;
		} else if (!flightNum.equals(other.flightNum))
			return false;
		if (leaveDate == null) {
			if (other.leaveDate != null)
				return false;
		} else if (!leaveDate.equals(other.leaveDate))
			return false;
		if (leaveTime == null) {
			if (other.leaveTime != null)
				return false;
		} else if (!leaveTime.equals(other.leaveTime))
			return false;
		if (numStops != other.numStops)
			return false;
		if (originAirport == null) {
			if (other.originAirport != null)
				return false;
		} else if (!originAirport.equals(other.originAirport))
			return false;
		if (originCity != other.originCity)
			return false;
		if (price != other.price)
			return false;
		if (seatsAvailable != other.seatsAvailable)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight [flightNum=" + flightNum + ", airlineID=" + airlineID + ", leaveTime=" + leaveTime
				+ ", leaveDate=" + leaveDate + ", arriveTime=" + arriveTime + ", arriveDate=" + arriveDate + ", price="
				+ price + ", numStops=" + numStops + ", seatsAvailable=" + seatsAvailable + ", originCity=" + originCity
				+ ", destCity=" + destCity + ", originAirport=" + originAirport + ", destAirport=" + destAirport + "]";
	}

}
