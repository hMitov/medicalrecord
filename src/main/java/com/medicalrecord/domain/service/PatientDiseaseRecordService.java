package com.medicalrecord.domain.service;

import com.medicalrecord.domain.adapter.PatientDiseaseRecordAdapter;
import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.dto.PatientDiseaseRecordData;
import com.medicalrecord.domain.dto.PatientDiseaseRecordDto;
import com.medicalrecord.domain.entity.Doctor;
import com.medicalrecord.domain.entity.Patient;
import com.medicalrecord.domain.entity.PatientDiseaseRecord;
import com.medicalrecord.domain.repository.DoctorRepository;
import com.medicalrecord.domain.repository.PatientDiseaseRecordRepository;
import com.medicalrecord.domain.repository.PatientRepository;
import com.medicalrecord.exceptions.DoctorNotFoundException;
import com.medicalrecord.exceptions.PatientDiseaseRecordNotFoundException;
import com.medicalrecord.exceptions.PatientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PatientDiseaseRecordService {


    @Autowired
    private PatientDiseaseRecordRepository patientDiseaseRecordRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientDiseaseRecordAdapter patientDiseaseRecordAdapter;


    public PatientDiseaseRecordDto addPatientDiseaseRecord(PatientDiseaseRecordData patientDiseaseData)
            throws PatientNotFoundException, DoctorNotFoundException, PatientDiseaseRecordNotFoundException {

        Patient patientLoaded = patientRepository.findByEgn(patientDiseaseData.getPatient().getEgn())
                .orElseThrow(() -> new PatientNotFoundException("No such patient found"));

        Doctor doctorLoaded = doctorRepository.findByEgn(patientDiseaseData.getDoctor().getEgn())
                .orElseThrow(() -> new DoctorNotFoundException("No such a doctor found"));

        Optional<PatientDiseaseRecord> patientDiseaseRecordLoaded = patientDiseaseRecordRepository
                .findByPatientAndDoctorAndVisitingTime(
                        patientLoaded,
                        doctorLoaded,
                        patientDiseaseData.getVisitingTime());

        if (patientDiseaseRecordLoaded.isPresent()) {
            throw new PatientDiseaseRecordNotFoundException(
                    "Disease record for this patient, doctor and visiting time already added in db");
        }

        PatientDiseaseRecord patientDiseaseRecord = new PatientDiseaseRecord();
        patientDiseaseRecord.setPatient(patientLoaded);
        patientDiseaseRecord.setDoctor(doctorLoaded);
        patientDiseaseRecord.setTreatment(patientDiseaseData.getTreatment());
        patientDiseaseRecord.setBeingOnSickLeave(patientDiseaseData.isBeingOnSickLeave());
        patientDiseaseRecord.setSickLeaveDuration(patientDiseaseData.getSickLeaveDuration());
        patientDiseaseRecord.setVisitingTime(patientDiseaseData.getVisitingTime());
        patientDiseaseRecord.setDiagnosis(patientDiseaseData.getDiagnosis());

        return patientDiseaseRecordAdapter.convertPatientDiseaseRecordEntityToDto(patientDiseaseRecordRepository
                .save(patientDiseaseRecord));

    }


    public PatientDiseaseRecordDto getPatientDiseaseRecord(Long recordId) throws PatientDiseaseRecordNotFoundException {

        PatientDiseaseRecord patientDiseaseRecordLoaded = patientDiseaseRecordRepository.findById(recordId)
                .orElseThrow(() -> new PatientDiseaseRecordNotFoundException("No such disease record for patient found"));

        return patientDiseaseRecordAdapter.convertPatientDiseaseRecordEntityToDto(patientDiseaseRecordLoaded);

    }


    public List<PatientDiseaseRecordDto> getAllPatientDiseaseRecords() {

        return patientDiseaseRecordRepository.findAll().stream()
                .map(patientDiseaseRecordAdapter::convertPatientDiseaseRecordEntityToDto)
                .collect(Collectors.toList());

    }


    public PatientDiseaseRecordDto updatePatientDiseaseRecord(Long recordId,
                                                              PatientDiseaseRecordData patientDiseaseData)
            throws PatientNotFoundException, DoctorNotFoundException {

        PatientDiseaseRecord patientDiseaseRecordLoaded = patientDiseaseRecordRepository.findById(recordId).isEmpty() ?
                new PatientDiseaseRecord() : patientDiseaseRecordRepository.findById(recordId).get();

        Patient patientLoaded = patientRepository
                .findByEgn(patientDiseaseData.getPatient().getEgn())
                .orElseThrow(() -> new PatientNotFoundException("No such a patient found"));

        Doctor doctorLoaded = doctorRepository
                .findByEgn(patientDiseaseData.getDoctor().getEgn())
                .orElseThrow(() -> new DoctorNotFoundException("No such a doctor found"));

        patientDiseaseRecordLoaded.setPatient(patientLoaded);
        patientDiseaseRecordLoaded.setDoctor(doctorLoaded);
        patientDiseaseRecordLoaded.setTreatment(patientDiseaseData.getTreatment());
        patientDiseaseRecordLoaded.setBeingOnSickLeave(patientDiseaseData.isBeingOnSickLeave());
        patientDiseaseRecordLoaded.setSickLeaveDuration(patientDiseaseData.getSickLeaveDuration());
        patientDiseaseRecordLoaded.setVisitingTime(patientDiseaseData.getVisitingTime());
        patientDiseaseRecordLoaded.setDiagnosis(patientDiseaseData.getDiagnosis());

        return patientDiseaseRecordAdapter.convertPatientDiseaseRecordEntityToDto(patientDiseaseRecordRepository
                .save(patientDiseaseRecordLoaded));

    }


    public void deletePatientDiseaseRecord(Long id) {
        patientDiseaseRecordRepository.deleteById(id);
    }

    public List<PatientDiseaseRecordDto> getPatientDiseaseRecordsByPatientNameAndEgn(PatientData data)
            throws PatientNotFoundException, PatientDiseaseRecordNotFoundException {

        Patient patientLoaded = patientRepository.findByEgn(data.getEgn())
                .orElseThrow(() -> new PatientNotFoundException("Not such a patient found"));

        List<PatientDiseaseRecord> patientDiseaseRecordsLoaded = patientDiseaseRecordRepository
                .findByPatientEgn(patientLoaded.getEgn())
                .orElseThrow(() -> new PatientDiseaseRecordNotFoundException("No such patient disease record found"));

        return patientDiseaseRecordsLoaded.stream().map(patientDiseaseRecordAdapter::convertPatientDiseaseRecordEntityToDto)
                .collect(Collectors.toList());

    }

}
