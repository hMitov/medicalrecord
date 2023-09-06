package com.medicalrecord.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class PatientDiseaseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @Column
    private String treatment;

    @Column
    private boolean beingOnSickLeave;

    @Column
    private int sickLeaveDuration;

    @Column
    private Date visitingTime;

    @Column
    private String diagnosis;

}
