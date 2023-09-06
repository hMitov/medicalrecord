package com.medicalrecord.web.adapter;

import com.medicalrecord.domain.dto.*;
import com.medicalrecord.domain.entity.GpPatientRegistration;
import com.medicalrecord.web.view.model.*;
import org.springframework.stereotype.Component;

@Component
public class GpPatientRegistrationModelViewAdapter {

    public GpPatientRegistrationModelView convertGpPatientRegistrationDtoToModelView(
            GpPatientRegistrationDto gpPatientRegistrationDto) {

        GpPatientRegistrationModelView gpPatientRegistrationModelView = new GpPatientRegistrationModelView();
        GpPatientRegistrationModelViewData gpPatientRegistrationData = new GpPatientRegistrationModelViewData();

        PatientModelViewDataLight patientModelViewData = new PatientModelViewDataLight();
        patientModelViewData.setFirstName(gpPatientRegistrationDto.getGpPatientRegistrationData().getPatient().getFirstName());
        patientModelViewData.setLastName(gpPatientRegistrationDto.getGpPatientRegistrationData().getPatient().getLastName());
        patientModelViewData.setEgn(gpPatientRegistrationDto.getGpPatientRegistrationData().getPatient().getEgn());

        gpPatientRegistrationData.setPatient(patientModelViewData);

        DoctorModelViewDataLight doctorModelViewData = new DoctorModelViewDataLight();
        doctorModelViewData.setFirstName(gpPatientRegistrationDto.getGpPatientRegistrationData().getDoctor().getFirstName());
        doctorModelViewData.setLastName(gpPatientRegistrationDto.getGpPatientRegistrationData().getDoctor().getLastName());
        doctorModelViewData.setEgn(gpPatientRegistrationDto.getGpPatientRegistrationData().getDoctor().getEgn());

        gpPatientRegistrationData.setDoctor(doctorModelViewData);

        gpPatientRegistrationModelView.setId(gpPatientRegistrationDto.getId());
        gpPatientRegistrationModelView.setGpPatientRegistrationData(gpPatientRegistrationData);

        return gpPatientRegistrationModelView;

    }

    public GpPatientRegistrationData convertGpPatientRegistrationModelViewDataToGpPatientData(
            GpPatientRegistrationModelViewData modelViewData) {

        GpPatientRegistrationData gpPatientRegistrationData = new GpPatientRegistrationData();

        PatientData patientData = new PatientData();
        patientData.setFirstName(modelViewData.getPatient().getFirstName());
        patientData.setLastName(modelViewData.getPatient().getLastName());
        patientData.setEgn(modelViewData.getPatient().getEgn());

        gpPatientRegistrationData.setPatient(patientData);

        DoctorData doctorData = new DoctorData();
        doctorData.setFirstName(modelViewData.getDoctor().getFirstName());
        doctorData.setLastName(modelViewData.getDoctor().getLastName());
        doctorData.setEgn(modelViewData.getDoctor().getEgn());

        gpPatientRegistrationData.setDoctor(doctorData);

        return gpPatientRegistrationData;

    }

    public GpPatientRegistrationData convertGpPatientRegistrationModelViewDataLightToGpPatientData(
            GpPatientRegistrationModelViewData modelViewData) {

        GpPatientRegistrationData gpPatientRegistrationData = new GpPatientRegistrationData();

        PatientData patientData = new PatientData();
        patientData.setFirstName(modelViewData.getPatient().getFirstName());
        patientData.setLastName(modelViewData.getPatient().getLastName());
        patientData.setEgn(modelViewData.getPatient().getEgn());

        gpPatientRegistrationData.setPatient(patientData);

        DoctorData doctorData = new DoctorData();
        doctorData.setFirstName(modelViewData.getDoctor().getFirstName());
        doctorData.setLastName(modelViewData.getDoctor().getLastName());
        doctorData.setEgn(modelViewData.getDoctor().getEgn());

        gpPatientRegistrationData.setDoctor(doctorData);

        return gpPatientRegistrationData;

    }

}
