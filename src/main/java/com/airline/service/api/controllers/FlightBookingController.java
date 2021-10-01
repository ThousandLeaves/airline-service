package com.airline.service.api.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletRequest;

import com.airline.service.api.services.PassengerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airline.service.api.entities.Flight;
import com.airline.service.api.entities.Passenger;
import com.airline.service.api.entities.User;
import com.airline.service.api.repos.FlightRepository;
import com.airline.service.api.repos.PassengerRepository;
import com.airline.service.api.services.FlightService;
import com.airline.service.api.services.PassengerService;
import com.airline.service.api.services.UserService;




@Controller
public class FlightBookingController {

	@Autowired
	FlightService flightService;
	@Autowired
	UserService userService;
	@Autowired
	PassengerService passengerService;

	 @RequestMapping("booking/findOne")
     public String findOne(String id,Model model) {
		 Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 String email;
		 if (principal instanceof UserDetails) {
		   email = ((UserDetails)principal).getUsername();

		 } else {
		   email = principal.toString();
		 }
		 User user=userService.findbyEmail(email);
   	  Flight flight= flightService.flightToBookById(id);
  	int seatNumber = ThreadLocalRandom.current().nextInt(1, flight.getSeatsAvailable() + 1);
   	  Passenger passenger=new Passenger();
   	  passenger.setUserEmail(email);
   	  passenger.setFirstName(user.getFirstName());
   	  passenger.setLastName(user.getLastName());
   	  passenger.setSeatNum(String.valueOf(seatNumber));
   	  passenger.setFlightNum(flight.getFlightNum());
   	  passenger.setBookingOrigin(0);

		 List<PassengerInfo> oldP = passengerService.getPassengerInfo(email);
		 if (oldP == null || oldP.isEmpty()) {
			 passengerService.saveFlight(passenger);
			 model.addAttribute("passenger", passenger);
			 return "flight/bookinginfo";
		 }

		 Passenger existancePassenger= oldP.get(0).passenger; //passengerService.isPresent(passenger.getFirstName(),passenger.getLastName(),passenger.getFlightNum());
			 model.addAttribute("passenger", existancePassenger);
		   return "forward:/flightbooking?failed";
     }
}
