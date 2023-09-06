package com.medicalrecord.web.adapter;

import com.medicalrecord.domain.dto.*;
import com.medicalrecord.web.view.model.*;
import org.springframework.stereotype.Component;

@Component
public class HealthInsuranceModelViewAdapter {

    public HealthInsuranceModelView convertHealthInsuranceDtoToModelView(HealthInsuranceDto healthInsuranceDto) {

        HealthInsuranceModelView healthInsuranceModelView = new HealthInsuranceModelView();
        HealthInsuranceModelViewData healthInsuranceModelViewData = new HealthInsuranceModelViewData();

        PatientModelViewDataLight patient = new PatientModelViewDataLight();
        patient.setFirstName(healthInsuranceDto.getHealthInsuranceData().getPatient().getFirstName());
        patient.setLastName(healthInsuranceDto.getHealthInsuranceData().getPatient().getLastName());
        patient.setEgn(healthInsuranceDto.getHealthInsuranceData().getPatient().getEgn());

        healthInsuranceModelViewData.setPatient(patient);
        healthInsuranceModelViewData.setHealthInsuranceFee(healthInsuranceDto.getHealthInsuranceData().getHealthInsuranceFee());
        healthInsuranceModelViewData.setMonth(healthInsuranceDto.getHealthInsuranceData().getMonth());

        healthInsuranceModelView.setId(healthInsuranceDto.getId());
        healthInsuranceModelView.setHealthInsuranceData(healthInsuranceModelViewData);

        return healthInsuranceModelView;

    }

    public HealthInsuranceData convertHealthInsuranceModelViewDataToHealthInsuranceData(HealthInsuranceModelViewData modelViewData) {

        HealthInsuranceData healthInsuranceData = new HealthInsuranceData();

        PatientData patientData = new PatientData();
        patientData.setFirstName(modelViewData.getPatient().getFirstName());
        patientData.setLastName(modelViewData.getPatient().getLastName());
        patientData.setEgn(modelViewData.getPatient().getEgn());

        healthInsuranceData.setPatient(patientData);
        healthInsuranceData.setHealthInsuranceFee(modelViewData.getHealthInsuranceFee());
        healthInsuranceData.setMonth(modelViewData.getMonth());

        return healthInsuranceData;

    }

}
