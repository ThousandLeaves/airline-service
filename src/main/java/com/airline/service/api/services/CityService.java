package com.airline.service.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.service.api.entities.City;
import com.airline.service.api.repos.CityRepository;

@Service
public class CityService {

	// Autowired not needed when single constructor exists
	private final CityRepository cityRepository;

	public CityService(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	public List<City> getAllCity(){
		return cityRepository.findAll();
	}
}
