package com.medicalrecord.domain.dto;

import lombok.Data;

@Data
public class PatientDiseaseRecordDto {

    private Long id;

    private PatientDiseaseRecordData patientDiseaseRecordData;

}
