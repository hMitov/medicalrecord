package com.medicalrecord.web.view.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Data
public class PatientDiseaseRecordModelViewData {

    @Valid
    private PatientModelViewDataLight patient;

    @Valid
    private DoctorModelViewDataLight doctor;

    @NotBlank
    private String treatment;

    @Min(value = 0, message = "Should be equal or greater than 0")
    private int sickLeaveDuration;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message="The date has to be in the past!")
    private Date visitingTime;

    @NotBlank
    private String diagnosis;

}

