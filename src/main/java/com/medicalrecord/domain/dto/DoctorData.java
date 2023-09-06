package com.medicalrecord.domain.dto;

import com.medicalrecord.domain.entity.Specialty;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class DoctorData {

    private String egn;

    private String firstName;

    private String lastName;

    private String age;

    private String workingPlace;

    private Set<Specialty> specialties;

}
