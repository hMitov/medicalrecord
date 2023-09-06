package com.medicalrecord.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class HealthInsuranceData {

    private PatientData patient;

    private BigDecimal healthInsuranceFee;

    private Date month;

}
