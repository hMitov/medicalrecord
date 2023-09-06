package com.medicalrecord.domain.adapter;

import com.medicalrecord.domain.dto.DoctorData;
import com.medicalrecord.domain.dto.DoctorDto;
import com.medicalrecord.domain.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorAdapter {

    public DoctorDto convertDoctorEntityToDto(Doctor doctor) {

        DoctorDto doctorDto = new DoctorDto();
        DoctorData doctorData = new DoctorData();

        doctorData.setAge(doctor.getAge());
        doctorData.setEgn(doctor.getEgn());
        doctorData.setFirstName(doctor.getFirstName());
        doctorData.setLastName(doctor.getLastName());
        doctorData.setSpecialties(doctor.getSpecialties());
        doctorData.setWorkingPlace(doctor.getWorkingPlace());

        doctorDto.setId(doctor.getId());
        doctorDto.setDoctorData(doctorData);

        return doctorDto;
    }

}
