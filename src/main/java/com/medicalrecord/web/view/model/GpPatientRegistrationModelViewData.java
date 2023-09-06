package com.medicalrecord.web.view.model;

import lombok.Data;

import javax.validation.Valid;

@Data
public class GpPatientRegistrationModelViewData {

    @Valid
    private PatientModelViewDataLight patient;

    @Valid
    private DoctorModelViewDataLight doctor;

}
