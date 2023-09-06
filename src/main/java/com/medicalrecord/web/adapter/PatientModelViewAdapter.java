package com.medicalrecord.web.adapter;

import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.dto.PatientDto;
import com.medicalrecord.web.view.model.PatientModelView;
import com.medicalrecord.web.view.model.PatientModelViewData;
import com.medicalrecord.web.view.model.PatientModelViewDataLight;
import org.springframework.stereotype.Component;

@Component
public class PatientModelViewAdapter {
    public PatientModelView convertPatientDtoToModelView(PatientDto patientDto) {

        PatientModelView patientModelView = new PatientModelView();
        PatientModelViewData patientModelViewData = new PatientModelViewData();

        patientModelViewData.setAge(patientDto.getPatientData().getAge());
        patientModelViewData.setEgn(patientDto.getPatientData().getEgn());
        patientModelViewData.setFirstName(patientDto.getPatientData().getFirstName());
        patientModelViewData.setLastName(patientDto.getPatientData().getLastName());

        patientModelView.setId(patientDto.getId());
        patientModelView.setPatientData(patientModelViewData);

        return patientModelView;

    }

    public PatientModelViewData convertPatientDtoToModelView(PatientData patientData) {

        PatientModelViewData patientModelViewData = new PatientModelViewData();

        patientModelViewData.setAge(patientData.getAge());
        patientModelViewData.setEgn(patientData.getEgn());
        patientModelViewData.setFirstName(patientData.getFirstName());
        patientModelViewData.setLastName(patientData.getLastName());

        return patientModelViewData;

    }

    public PatientDto convertPatientModelViewToDto(PatientModelView patientModelView) {

        PatientDto patientDto = new PatientDto();
        PatientData patientData = new PatientData();

        patientData.setAge(patientModelView.getPatientData().getAge());
        patientData.setEgn(patientModelView.getPatientData().getEgn());
        patientData.setFirstName(patientModelView.getPatientData().getFirstName());
        patientData.setLastName(patientModelView.getPatientData().getLastName());

        patientDto.setId(patientModelView.getId());
        patientDto.setPatientData(patientData);

        return patientDto;

    }

    public PatientData convertPatientModelViewDataToPatientData(PatientModelViewData patientModelViewData) {

        PatientData patientData = new PatientData();

        patientData.setAge(patientModelViewData.getAge());
        patientData.setEgn(patientModelViewData.getEgn());
        patientData.setFirstName(patientModelViewData.getFirstName());
        patientData.setLastName(patientModelViewData.getLastName());

        return patientData;

    }

    public PatientData convertPatientModelViewDataLightToPatientData(PatientModelViewDataLight patientModelViewDataLight) {

        PatientData patientData = new PatientData();

        patientData.setFirstName(patientModelViewDataLight.getFirstName());
        patientData.setLastName(patientModelViewDataLight.getLastName());
        patientData.setEgn(patientModelViewDataLight.getEgn());

        return patientData;

    }

}
