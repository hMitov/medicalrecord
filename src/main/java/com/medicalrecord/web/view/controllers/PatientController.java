package com.medicalrecord.web.view.controllers;

import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.service.PatientService;
import com.medicalrecord.exceptions.PatientNotFoundException;
import com.medicalrecord.web.adapter.PatientModelViewAdapter;
import com.medicalrecord.web.view.model.PatientModelView;
import com.medicalrecord.web.view.model.PatientModelViewData;
import com.medicalrecord.web.view.model.PatientModelViewDataLight;
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
@RequestMapping("/patients")
public class PatientController {

    private PatientModelViewAdapter patientModelViewAdapter;

    private PatientService patientService;

    @GetMapping
    public String getAllPatients(Model model) {

        List<PatientModelView> patients = patientService.getAllPatients().stream()
                .map(patientModelViewAdapter::convertPatientDtoToModelView).collect(Collectors.toList());
        model.addAttribute("patients", patients);

        return "/patients/patients.html";

    }

    @GetMapping("/create-patient")
    public String showCreatePatientForm(Model model) {

        model.addAttribute("patient", new PatientModelViewData());
        return "/patients/create-patient";

    }

    @PostMapping("/create")
    public String createPatient(@Valid @ModelAttribute("patient") PatientModelViewData patientData,
                                BindingResult bindingResult) throws PatientNotFoundException {

        if (bindingResult.hasErrors()) {
            return "/patients/create-patient";
        }
        patientService.addPatient(patientModelViewAdapter.convertPatientModelViewDataToPatientData(patientData));

        return "redirect:/patients";

    }

    @GetMapping("/edit-patient/{id}")
    public String showEditPatientForm(Model model, @PathVariable Long id) throws PatientNotFoundException {

        PatientModelView patient = patientModelViewAdapter.convertPatientDtoToModelView(patientService.getPatient(id));
        model.addAttribute("patient", patient.getPatientData());
        model.addAttribute("id", patient.getId());

        return "/patients/edit-patient";

    }

    @PostMapping("/update/{id}")
    public String updatePatient(@PathVariable long id,
                                @Valid @ModelAttribute("patient") PatientModelViewData patientData,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/patients/edit-patient";
        }
        patientService.updatePatient(id, patientModelViewAdapter.convertPatientModelViewDataToPatientData(patientData));

        return "redirect:/patients";

    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable Long id) {

        patientService.deletePatient(id);
        return "redirect:/patients";

    }

    @GetMapping("/search-patient")
    public String processSearchPatientForm(Model model) {
        model.addAttribute("patient", new PatientModelViewData());
        return "/patients/search-patient";
    }

    @GetMapping("/patient-name-egn")
    public String getPatientsByFirstNameAndLastNameEgn(Model model,
                                                       @Valid @ModelAttribute("patient") PatientModelViewDataLight patientModelViewData,
                                                       BindingResult bindingResult) throws PatientNotFoundException {

        if (bindingResult.hasErrors()) {
            return "/patients/search-patient";
        }
        PatientData patientData = patientModelViewAdapter.convertPatientModelViewDataLightToPatientData(patientModelViewData);
        List<PatientModelView> patients = patientService.getPatientsByFirstNameAndLastNameEgn(patientData).stream()
                .map(patientModelViewAdapter::convertPatientDtoToModelView).collect(Collectors.toList());

        model.addAttribute("patients", patients);
        return "/patients/patients";

    }

    @ExceptionHandler(PatientNotFoundException.class)
    public String handleException(PatientNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/patient-errors";
    }


}
