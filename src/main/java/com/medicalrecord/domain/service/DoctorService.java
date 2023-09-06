package com.medicalrecord.domain.service;

import com.medicalrecord.domain.adapter.DoctorAdapter;
import com.medicalrecord.domain.dto.DoctorData;
import com.medicalrecord.domain.dto.DoctorDto;
import com.medicalrecord.domain.entity.Doctor;
import com.medicalrecord.domain.repository.DoctorRepository;
import com.medicalrecord.exceptions.DoctorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorAdapter doctorAdapter;


    public DoctorDto addDoctor(DoctorData doctorData) throws DoctorNotFoundException {

        Optional<Doctor> doctorLoaded = doctorRepository.findByEgn(doctorData.getEgn());

        if (doctorLoaded.isPresent()) {
            throw new DoctorNotFoundException("Doctor already added in db");
        }

        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorData.getFirstName());
        doctor.setLastName(doctorData.getLastName());
        doctor.setAge(doctorData.getAge());
        doctor.setEgn(doctorData.getEgn());
        doctor.setSpecialties(doctorData.getSpecialties());
        doctor.setWorkingPlace(doctorData.getWorkingPlace());

        return doctorAdapter.convertDoctorEntityToDto(doctorRepository.save(doctor));

    }


    public DoctorDto getDoctor(Long doctorId) throws DoctorNotFoundException {

        Doctor doctorLoaded = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException("No such a doctor"));
        return doctorAdapter.convertDoctorEntityToDto(doctorLoaded);

    }


    public List<DoctorDto> getAllDoctors() {

        return doctorRepository.findAll().stream().map(doctorAdapter::convertDoctorEntityToDto)
                .collect(Collectors.toList());

    }


    public DoctorDto updateDoctor(Long doctorId, DoctorData doctorData) {

        Doctor doctorLoaded = doctorRepository.findById(doctorId).isEmpty() ?
                new Doctor() : doctorRepository.findById(doctorId).get();

        doctorLoaded.setFirstName(doctorData.getFirstName());
        doctorLoaded.setLastName(doctorData.getLastName());
        doctorLoaded.setAge(doctorData.getAge());
        doctorLoaded.setEgn(doctorData.getEgn());
        doctorLoaded.setSpecialties(doctorData.getSpecialties());
        doctorData.setWorkingPlace(doctorData.getWorkingPlace());

        return doctorAdapter.convertDoctorEntityToDto(doctorRepository.save(doctorLoaded));

    }


    public void deleteDoctor(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }


    public List<DoctorDto> getDoctorByFirstNameAndLastNameAndWorkingPlace(DoctorData doctorData) throws DoctorNotFoundException {

        List<Doctor> doctorsLoaded =
                doctorRepository.findByFirstNameAndLastNameAndWorkingPlace(doctorData.getFirstName(),
                                doctorData.getLastName(), doctorData.getWorkingPlace())
                        .orElseThrow(() -> new DoctorNotFoundException("No such doctors found"));
        return doctorsLoaded.stream().map(doctorAdapter::convertDoctorEntityToDto).collect(Collectors.toList());

    }

}
