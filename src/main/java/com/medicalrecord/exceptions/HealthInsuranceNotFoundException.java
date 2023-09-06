package com.medicalrecord.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class HealthInsuranceNotFoundException extends RuntimeException {
    public HealthInsuranceNotFoundException(String message) {
        super(message);
    }
}
