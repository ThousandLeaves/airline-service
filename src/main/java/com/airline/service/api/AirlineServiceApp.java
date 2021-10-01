package com.airline.service.api;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirlineServiceApp {

	@PostConstruct
	void started() {
	    TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(AirlineServiceApp.class, args);
	}

}
