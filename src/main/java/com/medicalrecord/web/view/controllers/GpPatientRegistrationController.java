package com.medicalrecord.web.view.controllers;

import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.service.GpPatientRegistrationService;
import com.medicalrecord.exceptions.DoctorNotFoundException;
import com.medicalrecord.exceptions.GpPatientRegistrationNotFoundException;
import com.medicalrecord.exceptions.PatientNotFoundException;
import com.medicalrecord.web.adapter.GpPatientRegistrationModelViewAdapter;
import com.medicalrecord.web.adapter.PatientModelViewAdapter;
import com.medicalrecord.web.view.model.GpPatientRegistrationModelView;
import com.medicalrecord.web.view.model.GpPatientRegistrationModelViewData;
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
@RequestMapping("/gp-patient-registrations")
public class GpPatientRegistrationController {

    private PatientModelViewAdapter patientModelViewAdapter;

    private GpPatientRegistrationModelViewAdapter modelViewAdapter;

    private GpPatientRegistrationService gpPatientRegistrationService;

    @GetMapping
    public String getAllGpPatientRegistrations(Model model) {

        List<GpPatientRegistrationModelView> gpPatientRegistrations =
                gpPatientRegistrationService.getAllGpPatientRegistrations().stream()
                        .map(modelViewAdapter::convertGpPatientRegistrationDtoToModelView).collect(Collectors.toList());
        model.addAttribute("gpPatientRegistrations", gpPatientRegistrations);

        return "/gp-patient-registrations/gp-patient-registrations.html";

    }

    @GetMapping("/create-gp-patient-registration")
    public String showCreateGpPatientRegistrationForm(Model model) {

        model.addAttribute("gpPatientRegistration", new GpPatientRegistrationModelViewData());
        return "/gp-patient-registrations/create-gp-patient-registration";

    }

    @PostMapping("/create")
    public String createGpPatientRegistration(@Valid @ModelAttribute("gpPatientRegistration")
                                                      GpPatientRegistrationModelViewData modelViewData,
                                              BindingResult bindingResult)
            throws GpPatientRegistrationNotFoundException, PatientNotFoundException, DoctorNotFoundException {

        if (bindingResult.hasErrors()) {
            return "/gp-patient-registrations/create-gp-patient-registration";
        }
        gpPatientRegistrationService.addNewGpPatientRegistration(
                modelViewAdapter.convertGpPatientRegistrationModelViewDataLightToGpPatientData(modelViewData)
        );

        return "redirect:/gp-patient-registrations";

    }

    @GetMapping("/edit-gp-patient-registration/{id}")
    public String showEditGpPatientRegistrationForm(Model model, @PathVariable Long id) throws GpPatientRegistrationNotFoundException {

        GpPatientRegistrationModelView gpPatientRegistration =
                modelViewAdapter.convertGpPatientRegistrationDtoToModelView(
                        gpPatientRegistrationService.getGpPatientRegistration(id)
                );
        model.addAttribute("gpPatientRegistration", gpPatientRegistration.getGpPatientRegistrationData());
        model.addAttribute("id", gpPatientRegistration.getId());

        return "/gp-patient-registrations/edit-gp-patient-registration";

    }

    @PostMapping("/update/{id}")
    public String updateGpPatientRegistration(@PathVariable long id,
                                              @Valid @ModelAttribute("gpPatientRegistration") GpPatientRegistrationModelViewData data,
                                              BindingResult bindingResult) throws PatientNotFoundException, DoctorNotFoundException {

        if (bindingResult.hasErrors()) {
            return "/gp-patient-registrations/edit-gp-patient-registration";
        }
        gpPatientRegistrationService.updateGpPatientRegistration(
                id, modelViewAdapter.convertGpPatientRegistrationModelViewDataToGpPatientData(data)
        );

        return "redirect:/gp-patient-registrations";

    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable Long id) {

        gpPatientRegistrationService.deleteGpPatientRegistration(id);
        return "redirect:/gp-patient-registrations";

    }

    @GetMapping("/search-gp-patient-registration")
    public String processSearchGpPatientRegistrationForm(Model model) {
        model.addAttribute("patient", new PatientModelViewData());
        return "/gp-patient-registrations/search-gp-patient-registration";

    }

    @GetMapping("/patient-name-egn")
    public String getGpPatientRegistrationsByPatientNameAndEgn(Model model,
                                                               @Valid @ModelAttribute("patient") PatientModelViewDataLight modelViewData,
                                                               BindingResult bindingResult)
            throws PatientNotFoundException, GpPatientRegistrationNotFoundException {

        if (bindingResult.hasErrors()) {
            return "/gp-patient-registrations/search-gp-patient-registration";
        }

        PatientData patientData = patientModelViewAdapter.convertPatientModelViewDataLightToPatientData(modelViewData);
        List<GpPatientRegistrationModelView> gpPatientRegistrations =
                gpPatientRegistrationService.getGpPatientRegistrationsByPatientNameAndEgn(patientData).stream()
                        .map(modelViewAdapter::convertGpPatientRegistrationDtoToModelView).collect(Collectors.toList());

        model.addAttribute("gpPatientRegistrations", gpPatientRegistrations);
        return "/gp-patient-registrations/gp-patient-registrations";

    }

    @ExceptionHandler(GpPatientRegistrationNotFoundException.class)
    public String handleException(GpPatientRegistrationNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/gp-patient-registration-errors";
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public String handleException(PatientNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/patient-errors";
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public String handleException(DoctorNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/doctor-errors";
    }

}
