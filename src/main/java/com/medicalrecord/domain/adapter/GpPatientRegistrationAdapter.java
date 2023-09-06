package com.medicalrecord.domain.adapter;

import com.medicalrecord.domain.dto.DoctorData;
import com.medicalrecord.domain.dto.GpPatientRegistrationData;
import com.medicalrecord.domain.dto.GpPatientRegistrationDto;
import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.entity.GpPatientRegistration;
import org.springframework.stereotype.Component;

@Component
public class GpPatientRegistrationAdapter {

    public GpPatientRegistrationDto convertGpPatientRegistrationEntityToDto(GpPatientRegistration gpPatientRegistration) {

        GpPatientRegistrationDto gpPatientRegistrationDto = new GpPatientRegistrationDto();
        GpPatientRegistrationData gpPatientRegistrationData = new GpPatientRegistrationData();

        PatientData patientData = new PatientData();
        patientData.setFirstName(gpPatientRegistration.getPatient().getFirstName());
        patientData.setLastName(gpPatientRegistration.getPatient().getLastName());
        patientData.setEgn(gpPatientRegistration.getPatient().getEgn());
        patientData.setAge(gpPatientRegistration.getPatient().getAge());

        gpPatientRegistrationData.setPatient(patientData);

        DoctorData doctorData = new DoctorData();
        doctorData.setFirstName(gpPatientRegistration.getDoctor().getFirstName());
        doctorData.setLastName(gpPatientRegistration.getDoctor().getLastName());
        doctorData.setEgn(gpPatientRegistration.getDoctor().getEgn());
        doctorData.setAge(gpPatientRegistration.getDoctor().getAge());
        doctorData.setWorkingPlace(gpPatientRegistration.getDoctor().getWorkingPlace());
        doctorData.setSpecialties(gpPatientRegistration.getDoctor().getSpecialties());

        gpPatientRegistrationData.setDoctor(doctorData);

        gpPatientRegistrationDto.setGpPatientRegistrationData(gpPatientRegistrationData);
        gpPatientRegistrationDto.setId(gpPatientRegistration.getId());

        return gpPatientRegistrationDto;

    }

}
