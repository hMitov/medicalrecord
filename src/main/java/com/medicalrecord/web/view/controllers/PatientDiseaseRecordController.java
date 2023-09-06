package com.medicalrecord.web.view.controllers;

import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.service.PatientDiseaseRecordService;
import com.medicalrecord.exceptions.DoctorNotFoundException;
import com.medicalrecord.exceptions.PatientDiseaseRecordNotFoundException;
import com.medicalrecord.exceptions.PatientNotFoundException;
import com.medicalrecord.web.adapter.PatientDiseaseRecordModelViewAdapter;
import com.medicalrecord.web.adapter.PatientModelViewAdapter;
import com.medicalrecord.web.view.model.PatientDiseaseRecordModelView;
import com.medicalrecord.web.view.model.PatientDiseaseRecordModelViewData;
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
@RequestMapping("/patient-disease-records")
public class PatientDiseaseRecordController {

    private PatientModelViewAdapter patientModelViewAdapter;

    private PatientDiseaseRecordModelViewAdapter patientDiseaseRecordModelViewAdapter;

    private PatientDiseaseRecordService patientDiseaseRecordService;

    @GetMapping
    public String getAllPatientDiseaseRecords(Model model) {

        List<PatientDiseaseRecordModelView> patientDiseaseRecords =
                patientDiseaseRecordService.getAllPatientDiseaseRecords().stream()
                        .map(patientDiseaseRecordModelViewAdapter::convertPatientDiseaseRecordDtoToModelView)
                        .collect(Collectors.toList());
        model.addAttribute("patientDiseaseRecords", patientDiseaseRecords);

        return "/patient-disease-records/patient-disease-records.html";

    }

    @GetMapping("/create-patient-disease-record")
    public String showCreatePatientDiseaseRecordForm(Model model) {

        model.addAttribute("patientDiseaseRecord", new PatientDiseaseRecordModelViewData());
        return "/patient-disease-records/create-patient-disease-record";

    }

    @PostMapping("/create")
    public String createPatientDiseaseRecord(@Valid @ModelAttribute("patientDiseaseRecord")
                                                     PatientDiseaseRecordModelViewData data,
                                             BindingResult bindingResult)
            throws PatientNotFoundException, DoctorNotFoundException, PatientDiseaseRecordNotFoundException {

        if (bindingResult.hasErrors()) {
            return "/patient-disease-records/create-patient-disease-record";
        }
        patientDiseaseRecordService.addPatientDiseaseRecord(
                patientDiseaseRecordModelViewAdapter.convertPatientDiseaseRecordModelViewDataToPatientDiseaseRecordData(data)
        );

        return "redirect:/patient-disease-records";

    }

    @GetMapping("/edit-patient-disease-record/{id}")
    public String showEditPatientDiseaseRecordsForm(Model model, @PathVariable Long id)
            throws PatientDiseaseRecordNotFoundException {

        PatientDiseaseRecordModelView patientDiseaseRecord =
                patientDiseaseRecordModelViewAdapter.convertPatientDiseaseRecordDtoToModelView(
                        patientDiseaseRecordService.getPatientDiseaseRecord(id)
                );
        model.addAttribute("patientDiseaseRecord", patientDiseaseRecord.getPatientDiseaseRecordData());
        model.addAttribute("id", patientDiseaseRecord.getId());

        return "/patient-disease-records/edit-patient-disease-record";

    }

    @PostMapping("/update/{id}")
    public String updatePatientDiseaseRecords(@PathVariable long id,
                                              @Valid @ModelAttribute("patientDiseaseRecord") PatientDiseaseRecordModelViewData data,
                                              BindingResult bindingResult)
            throws PatientNotFoundException, DoctorNotFoundException {

        if (bindingResult.hasErrors()) {
            return "/patient-disease-records/edit-patient-disease-record";
        }

        patientDiseaseRecordService.updatePatientDiseaseRecord(
                id, patientDiseaseRecordModelViewAdapter.convertPatientDiseaseRecordModelViewDataToPatientDiseaseRecordData(data)
        );

        return "redirect:/patient-disease-records";

    }

    @GetMapping("/delete/{id}")
    public String processProgramForm(@PathVariable Long id) {

        patientDiseaseRecordService.deletePatientDiseaseRecord(id);
        return "redirect:/patient-disease-records";

    }

    @GetMapping("/search-patient-disease-record")
    public String processSearchPatientDiseaseRecordForm(Model model) {
        model.addAttribute("patient", new PatientModelViewData());
        return "/patient-disease-records/search-patient-disease-record";

    }

    @GetMapping("/patient-name-egn")
    public String getPatientDiseaseRecordByPatientNameAndEgn(Model model,
                                                             @Valid @ModelAttribute("patient") PatientModelViewDataLight modelViewData,
                                                             BindingResult bindingResult)
            throws PatientNotFoundException, PatientDiseaseRecordNotFoundException {

        if (bindingResult.hasErrors()) {
            return "/patient-disease-records/search-patient-disease-record";
        }

        PatientData patientData = patientModelViewAdapter.convertPatientModelViewDataLightToPatientData(modelViewData);
        List<PatientDiseaseRecordModelView> patientDiseaseRecords =
                patientDiseaseRecordService.getPatientDiseaseRecordsByPatientNameAndEgn(patientData).stream()
                        .map(patientDiseaseRecordModelViewAdapter::convertPatientDiseaseRecordDtoToModelView)
                        .collect(Collectors.toList());

        model.addAttribute("patientDiseaseRecords", patientDiseaseRecords);
        return "/patient-disease-records/patient-disease-records";

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

    @ExceptionHandler(PatientDiseaseRecordNotFoundException.class)
    public String handleException(PatientDiseaseRecordNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "/errors/patient-disease-record-errors";
    }

}
