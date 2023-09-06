package com.medicalrecord.domain.service;

import com.medicalrecord.domain.adapter.GpPatientRegistrationAdapter;
import com.medicalrecord.domain.dto.GpPatientRegistrationData;
import com.medicalrecord.domain.dto.GpPatientRegistrationDto;
import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.entity.Doctor;
import com.medicalrecord.domain.entity.GpPatientRegistration;
import com.medicalrecord.domain.entity.Patient;
import com.medicalrecord.domain.repository.DoctorRepository;
import com.medicalrecord.domain.repository.GpPatientRegistrationRepository;
import com.medicalrecord.domain.repository.PatientRepository;
import com.medicalrecord.exceptions.DoctorNotFoundException;
import com.medicalrecord.exceptions.GpPatientRegistrationNotFoundException;
import com.medicalrecord.exceptions.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class GpPatientRegistrationService {


    @Autowired
    private GpPatientRegistrationRepository gpPatientRegistrationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private GpPatientRegistrationAdapter gpPatientRegistrationAdapter;


    public GpPatientRegistrationDto addNewGpPatientRegistration(GpPatientRegistrationData gpPatientRegistrationData)
            throws GpPatientRegistrationNotFoundException, PatientNotFoundException, DoctorNotFoundException {

        Patient patientLoaded = patientRepository
                .findByEgn(gpPatientRegistrationData.getPatient().getEgn())
                .orElseThrow(() -> new PatientNotFoundException("No such a patient found"));

        Doctor doctorLoaded = doctorRepository
                .findByEgn(gpPatientRegistrationData.getDoctor().getEgn())
                .orElseThrow(() -> new DoctorNotFoundException("No such a doctor found"));

        Optional<GpPatientRegistration> gpPatientRegistrationLoaded = gpPatientRegistrationRepository
                .findByPatientAndDoctor(patientLoaded, doctorLoaded);

        if (gpPatientRegistrationLoaded.isPresent()) {
            throw new GpPatientRegistrationNotFoundException("Gp patient registration already added in db");
        }

        GpPatientRegistration gpPatientRegistration = new GpPatientRegistration();
        gpPatientRegistration.setPatient(patientLoaded);
        gpPatientRegistration.setDoctor(doctorLoaded);

        return gpPatientRegistrationAdapter.convertGpPatientRegistrationEntityToDto(gpPatientRegistrationRepository
                .save(gpPatientRegistration));

    }


    public GpPatientRegistrationDto getGpPatientRegistration(Long gpPatientId) throws GpPatientRegistrationNotFoundException {

        GpPatientRegistration gpPatientRegistration = gpPatientRegistrationRepository.findById(gpPatientId)
                .orElseThrow(() -> new GpPatientRegistrationNotFoundException("No such a gp-patient registration found"));
        return gpPatientRegistrationAdapter.convertGpPatientRegistrationEntityToDto(gpPatientRegistration);

    }


    public List<GpPatientRegistrationDto> getAllGpPatientRegistrations() {

        return gpPatientRegistrationRepository.findAll().stream()
                .map(gpPatientRegistrationAdapter::convertGpPatientRegistrationEntityToDto)
                .collect(Collectors.toList());

    }


    public GpPatientRegistrationDto updateGpPatientRegistration(Long gpPatientId,
                                                                GpPatientRegistrationData gpPatientRegisterData)
            throws PatientNotFoundException, DoctorNotFoundException {

        GpPatientRegistration gpPatientRegistrationLoaded = gpPatientRegistrationRepository.findById(gpPatientId).isEmpty() ?
                new GpPatientRegistration() : gpPatientRegistrationRepository.findById(gpPatientId).get();

        Patient patientLoaded = patientRepository
                .findByEgn(gpPatientRegisterData.getPatient().getEgn())
                .orElseThrow(() -> new PatientNotFoundException("No such a patient found"));

        Doctor doctorLoaded = doctorRepository
                .findByEgn(gpPatientRegisterData.getDoctor().getEgn())
                .orElseThrow(() -> new DoctorNotFoundException("No such a doctor found"));

        gpPatientRegistrationLoaded.setPatient(patientLoaded);
        gpPatientRegistrationLoaded.setDoctor(doctorLoaded);

        return gpPatientRegistrationAdapter.convertGpPatientRegistrationEntityToDto(gpPatientRegistrationRepository
                .save(gpPatientRegistrationLoaded));

    }


    public void deleteGpPatientRegistration(Long id) {
        gpPatientRegistrationRepository.deleteById(id);
    }


    public List<GpPatientRegistrationDto> getGpPatientRegistrationsByPatientNameAndEgn(PatientData patientData)
            throws PatientNotFoundException, GpPatientRegistrationNotFoundException {

        Patient patientLoaded = patientRepository.findByEgn(patientData.getEgn())
                .orElseThrow(() -> new PatientNotFoundException("Not such a patient found"));

        List<GpPatientRegistration> gpPatientRegistrationsLoaded = gpPatientRegistrationRepository
                .findByPatientEgn(patientLoaded.getEgn())
                .orElseThrow(() -> new GpPatientRegistrationNotFoundException("No such gpPatientRegistration found"));

        return gpPatientRegistrationsLoaded.stream()
                .map(gpPatientRegistrationAdapter::convertGpPatientRegistrationEntityToDto).collect(Collectors.toList());

    }

}
