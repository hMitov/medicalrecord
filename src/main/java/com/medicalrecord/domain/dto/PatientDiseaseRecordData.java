package com.medicalrecord.domain.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PatientDiseaseRecordData {

    private PatientData patient;

    private DoctorData doctor;

    private String treatment;

    private boolean beingOnSickLeave;

    private int sickLeaveDuration;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visitingTime;

    private String diagnosis;

}
