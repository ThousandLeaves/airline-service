package com.airline.service.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PassengerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PassengerNotFoundException() {
        super("No such passenger ");
    }

    public PassengerNotFoundException(int id) {
        super("No such passenger" + Integer.toString(id));
    }

}
