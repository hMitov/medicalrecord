package com.medicalrecord.web.view.model;

import com.medicalrecord.domain.entity.Specialty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class DoctorModelViewData {

    @NotBlank
    @Size(min = 10, max = 10, message="Length of egn should be 10 numbers")
    private String egn;

    @NotBlank
    @Size(min = 2, max = 20, message="First name should be between 5 and 20 characters")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20, message="Last name should be between 5 and 20 characters")
    private String lastName;

    @NotBlank
    private String age;

    @NotBlank
    @Size(min = 2, max = 80, message="Working place should be between 2 and 30 characters")
    private String workingPlace;

    private Set<Specialty> specialties;

}
