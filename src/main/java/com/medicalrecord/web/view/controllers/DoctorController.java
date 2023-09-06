package com.medicalrecord.web.view.controllers;

import com.medicalrecord.domain.dto.DoctorData;
import com.medicalrecord.domain.dto.DoctorDto;
import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.service.DoctorService;
import com.medicalrecord.domain.service.SpecialtyService;
import com.medicalrecord.exceptions.DoctorNotFoundException;
import com.medicalrecord.web.adapter.DoctorModelViewAdapter;
import com.medicalrecord.web.view.model.*;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

    private static final Logger logger = LogManager.getLogger(DoctorController.class);

    private DoctorModelViewAdapter doctorModelViewAdapter;

    private DoctorService doctorService;

    private SpecialtyService specialtyService;

    @GetMapping
    public String getAllDoctors(Model model) {
        List<DoctorModelView> doctors = doctorService.getAllDoctors().stream()
                .map(doctorModelViewAdapter::convertDoctorDtoToModelView).collect(Collectors.toList());
        model.addAttribute("doctors", doctors);

        return "/doctors/doctors.html";

    }

    @GetMapping("/create-doctor")
    public String showCreateDoctorForm(Model model) {
        model.addAttribute("doctor", new DoctorModelViewData());
        model.addAttribute("specialties", specialtyService.getSpecialties());

        return "/doctors/create-doctor";

    }

    @PostMapping("/create")
    public String createDoctor(@Valid @ModelAttribute("doctor") DoctorModelViewData doctorData,
                               BindingResult bindingResult, Model model) throws DoctorNotFoundException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("specialties", specialtyService.getSpecialties());
            return "/doctors/create-doctor";
        }
        doctorService.addDoctor(doctorModelViewAdapter.convertDoctorModelViewDataToDoctorData(doctorData));

        return "redirect:/doctors";

    }

    @GetMapping("/edit-doctor/{id}")
    public String showEditDoctorForm(Model model, @PathVariable Long id) throws DoctorNotFoundException {

        DoctorModelView doctor = doctorModelViewAdapter.convertDoctorDtoToModelView(doctorService.getDoctor(id));
        model.addAttribute("doctor", doctor.getDoctorData());
        model.addAttribute("id", doctor.getId());
        model.addAttribute("specialties", specialtyService.getSpecialties());

        return "/doctors/edit-doctor";

    }

    @PostMapping("/update/{id}")
    public String updateDoctor(@PathVariable long id,
                               @Valid @ModelAttribute("doctor") DoctorModelViewData doctorData,
                               BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("specialties", specialtyService.getSpecialties());
            return "/doctors/edit-doctor";
        }
        doctorService.updateDoctor(id, doctorModelViewAdapter.convertDoctorModelViewDataToDoctorData(doctorData));

        return "redirect:/doctors";

    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable Long id) {

        doctorService.deleteDoctor(id);
        return "redirect:/doctors";

    }

    @GetMapping("/search-doctor")
    public String processSearchDoctorForm(Model model) {
        model.addAttribute("doctor", new DoctorModelViewData());
        return "/doctors/search-doctor";
    }

    @GetMapping("/doctor-name-working-place")
    public String getDoctorByFirstNameAndLastNameAndWorkingPlace(Model model,
                                                                 @Valid @ModelAttribute("doctor") SearchDoctorModelViewData doctorModelViewData,
                                                                 BindingResult bindingResult) throws DoctorNotFoundException {
        if (bindingResult.hasErrors()) {
            return "/doctors/search-doctor";
        }
        DoctorData doctorData = doctorModelViewAdapter.convertSearchDoctorModelViewDataToDoctorData(doctorModelViewData);
        List<DoctorModelView> doctors = doctorService.getDoctorByFirstNameAndLastNameAndWorkingPlace(doctorData).stream()
                .map(doctorModelViewAdapter::convertDoctorDtoToModelView).collect(Collectors.toList());

        model.addAttribute("doctors", doctors);
        return "/doctors/doctors";

    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public String handleException(DoctorNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/doctor-errors";
    }


}
