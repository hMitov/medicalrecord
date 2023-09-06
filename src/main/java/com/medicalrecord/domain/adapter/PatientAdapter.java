package com.medicalrecord.domain.adapter;

import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.dto.PatientDto;
import com.medicalrecord.domain.entity.Patient;
import org.springframework.stereotype.Component;


@Component
public class PatientAdapter {

    public PatientDto convertPatientEntityToDto(Patient patient) {

        PatientDto patientDto = new PatientDto();
        PatientData patientData = new PatientData();

        patientData.setAge(patient.getAge());
        patientData.setEgn(patient.getEgn());
        patientData.setFirstName(patient.getFirstName());
        patientData.setLastName(patient.getLastName());

        patientDto.setId(patient.getId());
        patientDto.setPatientData(patientData);

        return patientDto;

    }

}
