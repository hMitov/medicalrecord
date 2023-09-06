package com.medicalrecord.web.view.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class HealthInsuranceModelViewData {

    @Valid
    private PatientModelViewDataLight patient;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    private BigDecimal healthInsuranceFee;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past(message="The date has to be in the past!")
    private Date month;

}
