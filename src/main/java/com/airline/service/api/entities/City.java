package com.airline.service.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "city",schema = "g5_flights")
public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cityid" ,nullable = false)
	private int cityID;
	@Column(name = "cityname")
	private String cityName;
	@Column(name = "country")
	private String country;
	public City() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param cityID
	 * @param cityName
	 * @param country
	 */
	public City(int cityID, String cityName, String country) {
		super();
		this.cityID = cityID;
		this.cityName = cityName;
		this.country = country;
	}
	/**
	 * @return the cityID
	 */
	public int getCityID() {
		return cityID;
	}
	/**
	 * @param cityID the cityID to set
	 */
	public void setCityID(int cityID) {
		this.cityID = cityID;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
		@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		if (cityID != other.cityID)
			return false;
		if (cityName == null) {
			if (other.cityName != null)
				return false;
		} else if (!cityName.equals(other.cityName))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "City [cityID=" + cityID + ", cityName=" + cityName + ", country=" + country + "]";
	}
		
}
