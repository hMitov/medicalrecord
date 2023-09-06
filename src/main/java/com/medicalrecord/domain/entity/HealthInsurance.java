package com.medicalrecord.domain.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class HealthInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @Column
    private BigDecimal healthInsuranceFee;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date month;

    @Column
    private boolean isPaid;

}
