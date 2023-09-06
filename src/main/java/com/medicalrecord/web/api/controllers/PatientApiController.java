package com.medicalrecord.web.api.controllers;

import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.dto.PatientDto;
import com.medicalrecord.domain.service.PatientService;
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
@Api(tags = {"patient"}, value = "Patient", produces = APPLICATION_JSON_VALUE)
public class PatientApiController {


    @Autowired
    private PatientService patientService;


    @RequestMapping(path = "/patient", method = RequestMethod.POST)
    @ApiOperation(value = "Add patient", nickname = "addPatient")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PatientDto.class),
            @ApiResponse(code = 500, message = "Error adding a patient")})
    public PatientDto addPatient(@RequestBody PatientData patientData) throws Exception {

        return patientService.addPatient(patientData);

    }


    @RequestMapping(path = "/patient/{patientId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get patient", nickname = "getPatient")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PatientDto.class),
            @ApiResponse(code = 500, message = "Error getting patient")})
    public PatientDto getPatient(@PathVariable Long patientId) throws Exception {

        return patientService.getPatient(patientId);

    }


    @RequestMapping(path = "/patients-all", method = RequestMethod.GET)
    @ApiOperation(value = "Get all patients", nickname = "getAllPatients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PatientDto.class),
            @ApiResponse(code = 500, message = "Error getting patients")})
    public List<PatientDto> getAllPatients() {

        return patientService.getAllPatients();

    }


    @RequestMapping(path = "/patient/{patientId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update patient", nickname = "updatePatient")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PatientDto.class),
            @ApiResponse(code = 500, message = "Error updating patient")})
    public PatientDto updatePatient(@PathVariable Long patientId, @RequestBody PatientData patientData) {

        return patientService.updatePatient(patientId, patientData);

    }

}
