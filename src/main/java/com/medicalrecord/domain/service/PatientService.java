package com.medicalrecord.domain.service;

import com.medicalrecord.domain.adapter.PatientAdapter;
import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.dto.PatientDto;
import com.medicalrecord.domain.entity.Patient;
import com.medicalrecord.domain.repository.PatientRepository;
import com.medicalrecord.exceptions.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientAdapter patientAdapter;


    public PatientDto addPatient(PatientData patientData) throws PatientNotFoundException {

        Optional<Patient> patientLoaded = patientRepository.findByEgn(patientData.getEgn());

        if (patientLoaded.isPresent()) {
            throw new PatientNotFoundException("Patient already added in db");
        }

        Patient patient = new Patient();
        patient.setFirstName(patientData.getFirstName());
        patient.setLastName(patientData.getLastName());
        patient.setAge(patientData.getAge());
        patient.setEgn(patientData.getEgn());

        return patientAdapter.convertPatientEntityToDto(patientRepository.save(patient));

    }


    public PatientDto getPatient(Long patientId) throws PatientNotFoundException {

        Patient patientLoaded = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("No such a doctor"));
        return patientAdapter.convertPatientEntityToDto(patientLoaded);

    }


    public List<PatientDto> getAllPatients() {

        return patientRepository.findAll().stream().map(patientAdapter::convertPatientEntityToDto).collect(Collectors.toList());

    }


    public PatientDto updatePatient(Long patientId, PatientData patientData) {

        Patient patientLoaded = patientRepository.findById(patientId).isEmpty() ? new Patient() : patientRepository.findById(patientId).get();

        patientLoaded.setFirstName(patientData.getFirstName());
        patientLoaded.setLastName(patientData.getLastName());
        patientLoaded.setAge(patientData.getAge());
        patientLoaded.setEgn(patientData.getEgn());

        return patientAdapter.convertPatientEntityToDto(patientRepository.save(patientLoaded));

    }


    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public List<PatientDto> getPatientsByFirstNameAndLastNameEgn(PatientData patientData)
            throws PatientNotFoundException {

        List<Patient> patientsLoaded = patientRepository.findPatientsByEgn(patientData.getEgn())
                .orElseThrow(() -> new PatientNotFoundException("No such Patient found"));

        return patientsLoaded.stream().map(patientAdapter::convertPatientEntityToDto).collect(Collectors.toList());

    }
}
