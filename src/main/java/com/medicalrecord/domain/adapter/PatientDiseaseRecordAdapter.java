package com.medicalrecord.domain.adapter;

import com.medicalrecord.domain.dto.DoctorData;
import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.dto.PatientDiseaseRecordData;
import com.medicalrecord.domain.dto.PatientDiseaseRecordDto;
import com.medicalrecord.domain.entity.PatientDiseaseRecord;
import org.springframework.stereotype.Component;


@Component
public class PatientDiseaseRecordAdapter {

    public PatientDiseaseRecordDto convertPatientDiseaseRecordEntityToDto(PatientDiseaseRecord patientDiseaseRecord) {

        PatientDiseaseRecordDto patientDiseaseRecordDto = new PatientDiseaseRecordDto();
        PatientDiseaseRecordData patientDiseaseRecordData = new PatientDiseaseRecordData();

        PatientData patientData = new PatientData();
        patientData.setFirstName(patientDiseaseRecord.getPatient().getFirstName());
        patientData.setLastName(patientDiseaseRecord.getPatient().getLastName());
        patientData.setEgn(patientDiseaseRecord.getPatient().getEgn());
        patientData.setAge(patientDiseaseRecord.getPatient().getAge());

        patientDiseaseRecordData.setPatient(patientData);

        DoctorData doctorData = new DoctorData();
        doctorData.setFirstName(patientDiseaseRecord.getDoctor().getFirstName());
        doctorData.setLastName(patientDiseaseRecord.getDoctor().getLastName());
        doctorData.setEgn(patientDiseaseRecord.getDoctor().getEgn());
        doctorData.setAge(patientDiseaseRecord.getDoctor().getAge());
        doctorData.setWorkingPlace(patientDiseaseRecord.getDoctor().getWorkingPlace());
        doctorData.setSpecialties(patientDiseaseRecord.getDoctor().getSpecialties());

        patientDiseaseRecordData.setDoctor(doctorData);

        patientDiseaseRecordData.setTreatment(patientDiseaseRecord.getTreatment());
        patientDiseaseRecordData.setBeingOnSickLeave(patientDiseaseRecord.isBeingOnSickLeave());
        patientDiseaseRecordData.setSickLeaveDuration(patientDiseaseRecord.getSickLeaveDuration());
        patientDiseaseRecordData.setVisitingTime(patientDiseaseRecord.getVisitingTime());
        patientDiseaseRecordData.setDiagnosis(patientDiseaseRecord.getDiagnosis());

        patientDiseaseRecordDto.setId(patientDiseaseRecord.getId());
        patientDiseaseRecordDto.setPatientDiseaseRecordData(patientDiseaseRecordData);

        return patientDiseaseRecordDto;

    }

}
