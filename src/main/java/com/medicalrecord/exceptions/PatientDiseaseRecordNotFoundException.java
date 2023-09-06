package com.medicalrecord.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PatientDiseaseRecordNotFoundException extends RuntimeException {
    public PatientDiseaseRecordNotFoundException(String message) {
        super(message);
    }
}
