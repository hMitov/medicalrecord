package com.medicalrecord.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class GpPatientRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

}
