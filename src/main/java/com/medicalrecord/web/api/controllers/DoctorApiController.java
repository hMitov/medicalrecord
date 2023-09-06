package com.medicalrecord.web.api.controllers;

import com.medicalrecord.domain.dto.DoctorData;
import com.medicalrecord.domain.dto.DoctorDto;
import com.medicalrecord.domain.service.DoctorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/doctors")
@Api(tags = {"doctor"}, value = "Doctor", produces = APPLICATION_JSON_VALUE)
public class DoctorApiController {


    @Autowired
    private DoctorService doctorService;


    @RequestMapping(path = "/doctor-create", method = RequestMethod.POST)
    @ApiOperation(value = "Add doctor", nickname = "addDoctor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DoctorDto.class),
            @ApiResponse(code = 500, message = "Error adding a doctor")})
    public DoctorDto addDoctor(@RequestBody DoctorData doctorData) throws Exception {

        return doctorService.addDoctor(doctorData);

    }


    @RequestMapping(path = "/{doctorId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get doctor", nickname = "getDoctor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DoctorDto.class),
            @ApiResponse(code = 500, message = "Error getting doctor")})
    public DoctorDto getDoctor(@PathVariable Long doctorId) throws Exception {

        return doctorService.getDoctor(doctorId);

    }


    @GetMapping
    @ApiOperation(value = "Get all doctors", nickname = "getAllDoctors")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DoctorDto.class),
            @ApiResponse(code = 500, message = "Error getting doctors")})
    public List<DoctorDto> getAllDoctors() {

        return doctorService.getAllDoctors();

    }


    @RequestMapping(path = "/{doctorId}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update doctor", nickname = "updateDoctor")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DoctorDto.class),
            @ApiResponse(code = 500, message = "Error updating doctor")})
    public DoctorDto updateDoctor(@PathVariable Long doctorId, @RequestBody DoctorData doctorData) {

        return doctorService.updateDoctor(doctorId, doctorData);

    }

}
