package com.airline.service.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airline.service.api.entities.User;
import com.airline.service.api.dto.UserRegistrationDTO;
import com.airline.service.api.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	private final UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDTO userRegistrationDto() {
		return new UserRegistrationDTO();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDTO registrationDto) {

		if (registrationDto != null) {
			if (userService.isExist(registrationDto.getEmail())) {
				
				return "redirect:/registration?failed";
			} 
		} 

		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
}
