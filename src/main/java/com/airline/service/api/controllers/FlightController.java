package com.airline.service.api.controllers;

import java.util.List;

import com.airline.service.api.entities.User;
import com.airline.service.api.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.airline.service.api.entities.City;
import com.airline.service.api.entities.Flight;

@Controller
public class FlightController {

	private static Logger LOGGER = LoggerFactory.getLogger(FlightController.class.getName());
	@Autowired
	private FlightService flightService;
	@Autowired
	private CityService cityService;

	@Autowired
	UserService userService;

	@Autowired
	private PassengerService passengerService;

	public FlightController(FlightService flightService,CityService cityService) {
		this.flightService = flightService;
		this.cityService=cityService;
	}

	@RequestMapping(value = "/dd", method = RequestMethod.GET)
		public String retrieveFlight(Model model) {
	        model.addAttribute("viewFlights", flightService.getAllFlights());
	        return "f";
	    }


	private User getUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email;
		if (principal instanceof UserDetails) {
			email = ((UserDetails) principal).getUsername();

		} else {
			email = principal.toString();
		}

		User user = userService.findbyEmail(email);
		return user;
	}

	  @RequestMapping(value = "/flight", method = RequestMethod.GET)
	    public String getCities(Model model) {
		    List<City> cities = cityService.getAllCity();
		    model.addAttribute("cities", cities);
		    model.addAttribute("city", new City());

		    User user = getUser();

		  List<PassengerInfo> passengerInfoList = passengerService.getPassengerInfo(user.getEmail());

		  if(passengerInfoList != null) {
			  var passengerInfo = passengerInfoList.get(0);
			  model.addAttribute("passenger", passengerInfo.passenger);
			  model.addAttribute("passengerId", passengerInfo.passenger.getPassengerID());
		  }
		  else
			  model.addAttribute("passengerId", -1);

		  model.addAttribute("passengerValid", passengerInfoList != null);

		  return "flight/flight";

	    }
	  @RequestMapping(value = "/flight", method = RequestMethod.POST)
	    public String searchFlights(@RequestParam(required = true)String flightType,String adults,Model model, @ModelAttribute Flight flight,City city) {
		  int originCity = 0,destCity=0;
		 
		  if(city.getCityName()!=null) {
			  String cityID=city.getCityName();
			   originCity=Integer.valueOf(cityID.substring( 0, cityID.indexOf(",")));
			   destCity=Integer.valueOf(cityID.substring(cityID.indexOf(",")+1, cityID.length()));
					  
		  }
		  if(originCity!=0 && destCity!=0) {
			  flight.setOriginCity(originCity);
			  flight.setDestCity(destCity);
			  
		  }
		  if(adults!=null) {
			  flight.setSeatsAvailable(Integer.valueOf(adults));
		  }
		 
		  
		  model.addAttribute("searchFlights", flightService.searchFlightRecords(flight,flightType));
		System.out.println(model.toString());
	      //  return "redirect:/flight";
		  return "flight/result.html";
	    }
	   @GetMapping("/showNewFlightForm")
	    public String showNewFlightForm(Model model) {

	        Flight flight= new Flight();
	        model.addAttribute("flight", flight);
	        return "flight";
	    }
}
