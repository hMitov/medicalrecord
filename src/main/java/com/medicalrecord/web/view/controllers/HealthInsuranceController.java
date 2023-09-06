package com.medicalrecord.web.view.controllers;

import com.medicalrecord.domain.dto.GpPatientRegistrationData;
import com.medicalrecord.domain.dto.HealthInsuranceData;
import com.medicalrecord.domain.dto.HealthInsuranceDto;
import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.service.HealthInsuranceService;
import com.medicalrecord.exceptions.GpPatientRegistrationNotFoundException;
import com.medicalrecord.exceptions.HealthInsuranceNotFoundException;
import com.medicalrecord.exceptions.PatientNotFoundException;
import com.medicalrecord.web.adapter.HealthInsuranceModelViewAdapter;
import com.medicalrecord.web.adapter.PatientModelViewAdapter;
import com.medicalrecord.web.view.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/health-insurances")
public class HealthInsuranceController {

    private PatientModelViewAdapter patientModelViewAdapter;

    private HealthInsuranceModelViewAdapter healthInsuranceModelViewAdapter;

    private HealthInsuranceService healthInsuranceService;

    @GetMapping
    public String getAllHealthInsurances(Model model) {

        List<HealthInsuranceModelView> healthInsurances =
                healthInsuranceService.getAllHealthInsurances().stream()
                        .map(healthInsuranceModelViewAdapter::convertHealthInsuranceDtoToModelView).collect(Collectors.toList());
        model.addAttribute("healthInsurances", healthInsurances);

        return "/health-insurances/health-insurances.html";

    }

    @GetMapping("/create-health-insurance")
    public String showCreateHealthInsuranceForm(Model model) {

        model.addAttribute("healthInsurance", new HealthInsuranceModelViewData());
        return "/health-insurances/create-health-insurance";

    }

    @PostMapping("/create")
    public String createHealthInsurance(@Valid @ModelAttribute("healthInsurance") HealthInsuranceModelViewData modelViewData,
                                        BindingResult bindingResult)
            throws PatientNotFoundException, HealthInsuranceNotFoundException {

        if (bindingResult.hasErrors()) {
            return "/health-insurances/create-health-insurance";
        }
        healthInsuranceService.addHealthInsurance(
                healthInsuranceModelViewAdapter.convertHealthInsuranceModelViewDataToHealthInsuranceData(modelViewData)
        );

        return "redirect:/health-insurances";

    }

    @GetMapping("/edit-health-insurance/{id}")
    public String showEditHealthInsuranceForm(Model model, @PathVariable Long id) throws HealthInsuranceNotFoundException {

        HealthInsuranceModelView healthInsurance =
                healthInsuranceModelViewAdapter.convertHealthInsuranceDtoToModelView(
                        healthInsuranceService.getHealthInsurance(id)
                );
        model.addAttribute("healthInsurance", healthInsurance.getHealthInsuranceData());
        model.addAttribute("id", healthInsurance.getId());

        return "/health-insurances/edit-health-insurance";

    }

    @PostMapping("/update/{id}")
    public String updateHealthInsurance(@PathVariable long id,
                                        @Valid @ModelAttribute("healthInsurance") HealthInsuranceModelViewData data,
                                        BindingResult bindingResult)
            throws PatientNotFoundException, HealthInsuranceNotFoundException {

        if (bindingResult.hasErrors()) {
            return "/health-insurances/edit-health-insurance";
        }
        healthInsuranceService.updateHealthInsurance(
                id, healthInsuranceModelViewAdapter.convertHealthInsuranceModelViewDataToHealthInsuranceData(data)
        );

        return "redirect:/health-insurances";

    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable Long id) {

        healthInsuranceService.deleteHealthInsurance(id);
        return "redirect:/health-insurances";

    }

    @GetMapping("/search-health-insurances")
    public String processSearchHealthInsuranceForm(Model model) {
        model.addAttribute("patient", new PatientModelViewData());
        return "/health-insurances/search-health-insurance";
    }

    @GetMapping("/patient-name-egn")
    public String getHealthInsurancesByPatientNameAndEgn(Model model,
                                                         @Valid @ModelAttribute("patient") PatientModelViewDataLight patientModelViewData,
                                                         BindingResult bindingResult)
            throws PatientNotFoundException, HealthInsuranceNotFoundException {

        if (bindingResult.hasErrors()) {
            return "/health-insurances/search-health-insurance";
        }
        PatientData patientData = patientModelViewAdapter.convertPatientModelViewDataLightToPatientData(patientModelViewData);
        List<HealthInsuranceModelView> healthInsurances =
                healthInsuranceService.getHealthInsurancesByPatientNameAndEgn(patientData).stream()
                        .map(healthInsuranceModelViewAdapter::convertHealthInsuranceDtoToModelView)
                        .collect(Collectors.toList());

        model.addAttribute("healthInsurances", healthInsurances);
        return "/health-insurances/health-insurances";

    }

    @ExceptionHandler(PatientNotFoundException.class)
    public String handleException(PatientNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/patient-errors";
    }

    @ExceptionHandler(HealthInsuranceNotFoundException.class)
    public String handleException(HealthInsuranceNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/health-insurance-errors";
    }

}
