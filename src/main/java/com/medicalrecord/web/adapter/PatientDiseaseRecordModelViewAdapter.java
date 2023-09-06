package com.medicalrecord.web.adapter;

import com.medicalrecord.domain.dto.*;
import com.medicalrecord.web.view.model.*;
import org.springframework.stereotype.Component;

@Component
public class PatientDiseaseRecordModelViewAdapter {

    public PatientDiseaseRecordModelView convertPatientDiseaseRecordDtoToModelView(PatientDiseaseRecordDto patientDiseaseRecordDto) {

        PatientDiseaseRecordModelView patientDiseaseRecordModelView = new PatientDiseaseRecordModelView();
        PatientDiseaseRecordModelViewData patientDiseaseRecordModelViewData = new PatientDiseaseRecordModelViewData();

        PatientModelViewDataLight patient = new PatientModelViewDataLight();
        patient.setFirstName(patientDiseaseRecordDto.getPatientDiseaseRecordData().getPatient().getFirstName());
        patient.setLastName(patientDiseaseRecordDto.getPatientDiseaseRecordData().getPatient().getLastName());
        patient.setEgn(patientDiseaseRecordDto.getPatientDiseaseRecordData().getPatient().getEgn());

        patientDiseaseRecordModelViewData.setPatient(patient);

        DoctorModelViewDataLight doctor = new DoctorModelViewDataLight();
        doctor.setFirstName(patientDiseaseRecordDto.getPatientDiseaseRecordData().getDoctor().getFirstName());
        doctor.setLastName(patientDiseaseRecordDto.getPatientDiseaseRecordData().getDoctor().getLastName());
        doctor.setEgn(patientDiseaseRecordDto.getPatientDiseaseRecordData().getDoctor().getEgn());

        patientDiseaseRecordModelViewData.setDoctor(doctor);

        patientDiseaseRecordModelViewData.setTreatment(patientDiseaseRecordDto.getPatientDiseaseRecordData().getTreatment());
        patientDiseaseRecordModelViewData.setDiagnosis(patientDiseaseRecordDto.getPatientDiseaseRecordData().getDiagnosis());
        patientDiseaseRecordModelViewData.setVisitingTime(patientDiseaseRecordDto.getPatientDiseaseRecordData().getVisitingTime());
        patientDiseaseRecordModelViewData.setSickLeaveDuration(patientDiseaseRecordDto.getPatientDiseaseRecordData().getSickLeaveDuration());

        patientDiseaseRecordModelView.setId(patientDiseaseRecordDto.getId());
        patientDiseaseRecordModelView.setPatientDiseaseRecordData(patientDiseaseRecordModelViewData);

        return patientDiseaseRecordModelView;

    }

    public PatientDiseaseRecordData convertPatientDiseaseRecordModelViewDataToPatientDiseaseRecordData(PatientDiseaseRecordModelViewData modelViewData) {

        PatientDiseaseRecordData patientDiseaseRecordData = new PatientDiseaseRecordData();

        PatientData patient = new PatientData();
        patient.setFirstName(modelViewData.getPatient().getFirstName());
        patient.setLastName(modelViewData.getPatient().getLastName());
        patient.setEgn(modelViewData.getPatient().getEgn());

        patientDiseaseRecordData.setPatient(patient);

        DoctorData doctor = new DoctorData();
        doctor.setFirstName(modelViewData.getDoctor().getFirstName());
        doctor.setLastName(modelViewData.getDoctor().getLastName());
        doctor.setEgn(modelViewData.getDoctor().getEgn());

        patientDiseaseRecordData.setDoctor(doctor);

        patientDiseaseRecordData.setTreatment(modelViewData.getTreatment());
        patientDiseaseRecordData.setDiagnosis(modelViewData.getDiagnosis());
        patientDiseaseRecordData.setVisitingTime(modelViewData.getVisitingTime());
        patientDiseaseRecordData.setSickLeaveDuration(modelViewData.getSickLeaveDuration());

        return patientDiseaseRecordData;

    }

}
