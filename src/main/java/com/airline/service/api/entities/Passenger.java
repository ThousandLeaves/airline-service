package com.airline.service.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="passenger")
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="passengerid")
	private int passengerID;

	@Column(name="userEmail")
	private String userEmail;

	@Column(name="firstname")
	private String firstName;
	@Column (name="lastname")
	private String lastName;
	@Column (name="seatnum")
	private String seatNum;
	@Column (name="flightnum")
	private String flightNum;
	@Column (name="class")
	private int seatClass;
	// bookingOrigin must not be set by constructor
	// Will be set by the function used to make the booking
	@Column(name="bookingorigin")
	private int bookingOrigin;
	
	public Passenger() { 
		// Default constructor
	}
	
	public Passenger(/*int passengerID,*/ String firstName, String lastName, String seatNum, String flightNum,
			int seatClass) {
		super();
		//this.passengerID = passengerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.seatNum = seatNum;
		this.flightNum = flightNum;
		this.seatClass = seatClass;
	}

	public Passenger(int passengerID, String email, String firstName, String lastName, String seatNum, String flightNum,
					 int seatClass) {
		super();
		//this.passengerID = passengerID;
		this.userEmail = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.seatNum = seatNum;
		this.flightNum = flightNum;
		this.seatClass = seatClass;
	}
	
	// Cross-site passenger booking constructor
	public Passenger(String firstName, String lastName, String flightNum) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.flightNum = flightNum;
	}
	
	// Setters, getters
	public int getPassengerID() {
		return passengerID;
	}

	public void setPassengerID(int passengerID) {
		this.passengerID = passengerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	public int getSeatClass() {
		return seatClass;
	}

	public void setSeatClass(int seatClass) {
		this.seatClass = seatClass;
	}
	
	public int getBookingOrigin() {
		return bookingOrigin;
	}
	
	public void setBookingOrigin(int bookingOrigin) {
		this.bookingOrigin = bookingOrigin;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		if (bookingOrigin != other.bookingOrigin)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (flightNum == null) {
			if (other.flightNum != null)
				return false;
		} else if (!flightNum.equals(other.flightNum))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (passengerID != other.passengerID)
			return false;
		if (seatClass != other.seatClass)
			return false;
		if (seatNum == null) {
			if (other.seatNum != null)
				return false;
		} else if (!seatNum.equals(other.seatNum))
			return false;
		return true;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	// Custom toString
	/*@Override
	public String toString() {
		return "Passenger [passengerID=" + passengerID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", seatNum=" + seatNum + ", flightNum=" + flightNum + ", seatClass=" + seatClass + "]";
	}*/

	@Override
	public String toString() {
		return "Passenger{" +
				"passengerID=" + passengerID +
				", userEmail='" + userEmail + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", seatNum='" + seatNum + '\'' +
				", flightNum='" + flightNum + '\'' +
				", seatClass=" + seatClass +
				'}';
	}


	

}

