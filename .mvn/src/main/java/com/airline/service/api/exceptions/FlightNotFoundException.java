package com.airline.service.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FlightNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FlightNotFoundException() {
		super("No such flight list ");
	}

	public FlightNotFoundException(String id) {
		super("No such flight" + id);
	}
}
