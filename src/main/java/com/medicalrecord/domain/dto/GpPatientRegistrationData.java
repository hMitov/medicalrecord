package com.medicalrecord.domain.dto;

import lombok.Data;

@Data
public class GpPatientRegistrationData {

    private PatientData patient;

    private DoctorData doctor;

}
