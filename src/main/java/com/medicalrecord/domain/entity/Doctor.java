package com.medicalrecord.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String egn;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String age;

    @Column
    private String workingPlace;

    @ElementCollection
    @Column(name="specialty")
    @Enumerated(EnumType.STRING)
    private Set<Specialty> specialties;

    @OneToMany(mappedBy = "doctor")
    private List<GpPatientRegistration> gpPatientRegistration;

}
