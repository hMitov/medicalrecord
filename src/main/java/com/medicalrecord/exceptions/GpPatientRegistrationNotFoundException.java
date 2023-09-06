package com.medicalrecord.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GpPatientRegistrationNotFoundException extends RuntimeException {
    public GpPatientRegistrationNotFoundException(String message) {
        super(message);
    }
}
