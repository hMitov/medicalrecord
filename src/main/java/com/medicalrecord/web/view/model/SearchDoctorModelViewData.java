package com.medicalrecord.web.view.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SearchDoctorModelViewData {

    @NotBlank
    @Size(min = 2, max = 20, message="First name should be between 5 and 20 characters")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20, message="Last name should be between 5 and 20 characters")
    private String lastName;

    @NotBlank
    @Size(min = 2, max = 80, message="Working place should be between 5 and 20 characters")
    private String workingPlace;

}
