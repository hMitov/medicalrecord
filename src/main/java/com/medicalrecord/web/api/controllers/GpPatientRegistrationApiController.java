package com.medicalrecord.web.api.controllers;

import com.medicalrecord.domain.dto.GpPatientRegistrationData;
import com.medicalrecord.domain.dto.GpPatientRegistrationDto;
import com.medicalrecord.domain.dto.HealthInsuranceDto;
import com.medicalrecord.domain.service.GpPatientRegistrationService;
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
@Api(tags = {"gp-patient-register"}, value = "Gp Patient Register", produces = APPLICATION_JSON_VALUE)
public class GpPatientRegistrationApiController {

    @Autowired
    private GpPatientRegistrationService gpPatientRegistrationService;


    @RequestMapping(path = "/gp-patient-registration", method = RequestMethod.POST)
    @ApiOperation(value = "Add new gp-patient registration", nickname = "addNewGpPatientRegistration")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = GpPatientRegistrationDto.class),
            @ApiResponse(code = 500, message = "Error adding new gp-patient registration")})
    public GpPatientRegistrationDto addNewGpPatientRegistration(@RequestBody GpPatientRegistrationData gpPatientRegistrationData)
            throws Exception {

        return gpPatientRegistrationService.addNewGpPatientRegistration(gpPatientRegistrationData);

    }


    @RequestMapping(path = "/gp-patient-registration/{gpPatientId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get gp-patient registration", nickname = "getGpPatientRegistration")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = GpPatientRegistrationDto.class),
            @ApiResponse(code = 500, message = "Error getting gp-patient registration")})
    public GpPatientRegistrationDto getGpPatientRegistration(@PathVariable Long gpPatientId) throws Exception {

        return gpPatientRegistrationService.getGpPatientRegistration(gpPatientId);

    }


    @RequestMapping(path = "/gp-patient-registrations", method = RequestMethod.GET)
    @ApiOperation(value = "Get all gp-patient registrations", nickname = "getAllGpPatientRegistrations")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = GpPatientRegistrationDto.class),
            @ApiResponse(code = 500, message = "Error getting all gp-patient registrations")})
    public List<GpPatientRegistrationDto> getAllGpPatientRegistrations() {

        return gpPatientRegistrationService.getAllGpPatientRegistrations();

    }


    @RequestMapping(path = "/gp-patient-registration/{gpPatientId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update gp-patient registration", nickname = "updateGpPatientRegistration")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HealthInsuranceDto.class),
            @ApiResponse(code = 500, message = "Error updating gp-patient registration")})
    public GpPatientRegistrationDto updateGpPatientRegistration(@PathVariable Long gpPatientId,
                                                                @RequestBody GpPatientRegistrationData gpPatientRegistrationData) throws Exception {

        return gpPatientRegistrationService.updateGpPatientRegistration(gpPatientId, gpPatientRegistrationData);

    }

}
