package com.airline.service.api.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Component
public class UserRegistrationDTO {

	private String firstName;
	private String lastName;

	@Email(message="Email should be valid")
	@Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
	private String email;
	private String password;
	
	public UserRegistrationDTO(){
		
	}
	
	public UserRegistrationDTO(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
