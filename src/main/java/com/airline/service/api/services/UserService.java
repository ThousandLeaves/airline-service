package com.airline.service.api.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.airline.service.api.entities.User;
import com.airline.service.api.dto.UserRegistrationDTO;

public interface UserService extends UserDetailsService{

	User save(UserRegistrationDTO registrationDto);
	boolean isExist(String email);
	User findbyEmail(String email);

}