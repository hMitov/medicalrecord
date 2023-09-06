package com.medicalrecord.web.api.controllers;

import com.medicalrecord.domain.dto.HealthInsuranceData;
import com.medicalrecord.domain.dto.HealthInsuranceDto;
import com.medicalrecord.domain.service.HealthInsuranceService;
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
@Api(tags = {"health-insurance"}, value = "Health Insurance", produces = APPLICATION_JSON_VALUE)
public class HealthInsuranceApiController {


    @Autowired
    private HealthInsuranceService healthInsuranceService;


    @RequestMapping(path = "/health-insurance", method = RequestMethod.POST)
    @ApiOperation(value = "Add health insurance", nickname = "addHealthInsurance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HealthInsuranceDto.class),
            @ApiResponse(code = 500, message = "Error adding a health insurance")})
    public HealthInsuranceDto addHealthInsurance(@RequestBody HealthInsuranceData healthInsuranceData)
            throws Exception {

        return healthInsuranceService.addHealthInsurance(healthInsuranceData);

    }


    @RequestMapping(path = "/health-insurance/{healthInsuranceId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get health insurance", nickname = "getHealthInsurance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HealthInsuranceDto.class),
            @ApiResponse(code = 500, message = "Error getting health insurance")})
    public HealthInsuranceDto getHealthInsurance(@PathVariable Long healthInsuranceId) throws Exception {

        return healthInsuranceService.getHealthInsurance(healthInsuranceId);

    }


    @RequestMapping(path = "/health-insurances-all", method = RequestMethod.GET)
    @ApiOperation(value = "Get all health insurances", nickname = "getAllHealthInsurances")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HealthInsuranceDto.class),
            @ApiResponse(code = 500, message = "Error getting health insurances")})
    public List<HealthInsuranceDto> getAllHealthInsurances() {

        return healthInsuranceService.getAllHealthInsurances();

    }


    @RequestMapping(path = "/health-insurance/{healthInsuranceId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update health insurance", nickname = "updateHealthInsurance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HealthInsuranceDto.class),
            @ApiResponse(code = 500, message = "Error updating health insurance")})
    public HealthInsuranceDto updateDoctor(@PathVariable Long healthInsuranceId,
                                           @RequestBody HealthInsuranceData healthInsuranceData) throws Exception {
        return healthInsuranceService.updateHealthInsurance(healthInsuranceId, healthInsuranceData);
    }

}
