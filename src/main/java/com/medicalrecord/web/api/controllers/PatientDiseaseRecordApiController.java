package com.medicalrecord.web.api.controllers;

import com.medicalrecord.domain.dto.PatientDiseaseRecordData;
import com.medicalrecord.domain.dto.PatientDiseaseRecordDto;
import com.medicalrecord.domain.dto.PatientDto;
import com.medicalrecord.domain.service.PatientDiseaseRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(path = "/api")
@Api(tags = {"patient-disease-record"}, value = "Patient Disease Record", produces = APPLICATION_JSON_VALUE)
public class PatientDiseaseRecordApiController {


    @Autowired
    private PatientDiseaseRecordService patientDiseaseRecordService;


    @RequestMapping(path = "/patient-disease-record", method = RequestMethod.POST)
    @ApiOperation(value = "Add patient disease record", nickname = "addPatientDiseaseRecord")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PatientDiseaseRecordDto.class),
            @ApiResponse(code = 500, message = "Error adding a patient disease record")})
    public PatientDiseaseRecordDto addPatientDiseaseRecord(@RequestBody PatientDiseaseRecordData patientDiseaseData)
            throws Exception {

        return patientDiseaseRecordService.addPatientDiseaseRecord(patientDiseaseData);

    }


    @RequestMapping(path = "/patient-disease-record/{recordId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get patient disease record", nickname = "getPatientDiseaseRecord")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PatientDto.class),
            @ApiResponse(code = 500, message = "Error getting patient disease record")})
    public PatientDiseaseRecordDto getPatientDiseaseRecord(@PathVariable Long recordId) throws Exception {

        return patientDiseaseRecordService.getPatientDiseaseRecord(recordId);

    }


    @RequestMapping(path = "/patient-disease-records-all", method = RequestMethod.GET)
    @ApiOperation(value = "Get all patient disease records", nickname = "getAllPatientDiseaseRecords")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PatientDto.class),
            @ApiResponse(code = 500, message = "Error getting patient disease records")})
    public List<PatientDiseaseRecordDto> getAllPatientDiseaseRecord() {

        return patientDiseaseRecordService.getAllPatientDiseaseRecords();

    }


    @RequestMapping(path = "/patient-disease-record/{recordId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update patient disease record", nickname = "updatePatientDiseaseRecord")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PatientDto.class),
            @ApiResponse(code = 500, message = "Error updating patient disease record")})
    public PatientDiseaseRecordDto updatePatientDiseaseRecord(@PathVariable Long recordId,
                                                              @RequestBody PatientDiseaseRecordData patientDiseaseData)
            throws Exception {

        return patientDiseaseRecordService.updatePatientDiseaseRecord(recordId, patientDiseaseData);

    }

}
