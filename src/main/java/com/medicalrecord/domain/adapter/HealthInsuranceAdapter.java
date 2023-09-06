package com.medicalrecord.domain.adapter;

import com.medicalrecord.domain.dto.HealthInsuranceData;
import com.medicalrecord.domain.dto.HealthInsuranceDto;
import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.entity.HealthInsurance;
import org.springframework.stereotype.Component;


@Component
public class HealthInsuranceAdapter {

    public HealthInsuranceDto convertHealthInsuranceEntityToDto(HealthInsurance healthInsurance) {

        HealthInsuranceDto healthInsuranceDto = new HealthInsuranceDto();
        HealthInsuranceData healthInsuranceData = new HealthInsuranceData();

        PatientData patientData = new PatientData();
        patientData.setFirstName(healthInsurance.getPatient().getFirstName());
        patientData.setLastName(healthInsurance.getPatient().getLastName());
        patientData.setEgn(healthInsurance.getPatient().getEgn());
        patientData.setAge(healthInsurance.getPatient().getAge());

        healthInsuranceData.setPatient(patientData);
        healthInsuranceData.setHealthInsuranceFee(healthInsurance.getHealthInsuranceFee());
        healthInsuranceData.setMonth(healthInsurance.getMonth());

        healthInsuranceDto.setId(healthInsurance.getId());
        healthInsuranceDto.setHealthInsuranceData(healthInsuranceData);

        return healthInsuranceDto;

    }


}
