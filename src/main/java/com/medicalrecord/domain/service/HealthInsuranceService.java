package com.medicalrecord.domain.service;

import com.medicalrecord.domain.adapter.HealthInsuranceAdapter;
import com.medicalrecord.domain.dto.HealthInsuranceData;
import com.medicalrecord.domain.dto.HealthInsuranceDto;
import com.medicalrecord.domain.dto.PatientData;
import com.medicalrecord.domain.entity.HealthInsurance;
import com.medicalrecord.domain.entity.Patient;
import com.medicalrecord.domain.repository.HealthInsuranceRepository;
import com.medicalrecord.domain.repository.PatientRepository;
import com.medicalrecord.exceptions.HealthInsuranceNotFoundException;
import com.medicalrecord.exceptions.PatientNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HealthInsuranceService {

    private static final Logger logger = LogManager.getLogger(HealthInsuranceService.class);

    @Autowired
    private HealthInsuranceRepository healthInsuranceRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HealthInsuranceAdapter healthInsuranceAdapter;


    public HealthInsuranceDto addHealthInsurance(HealthInsuranceData healthInsuranceData)
            throws PatientNotFoundException, HealthInsuranceNotFoundException {

        logger.info("HealthInsuranceData: " + healthInsuranceData);

        Patient patientLoaded = patientRepository
                .findByEgn(healthInsuranceData.getPatient().getEgn())
                .orElseThrow(() -> new PatientNotFoundException("No such patient found"));

        Optional<HealthInsurance> healthInsuranceLoaded = healthInsuranceRepository
                .findByPatientAndMonth(patientLoaded, healthInsuranceData.getMonth());

        if (healthInsuranceLoaded.isPresent()) {
            throw new HealthInsuranceNotFoundException("Health insurance for this patient and month already added in db");
        }

        HealthInsurance healthInsurance = new HealthInsurance();
        healthInsurance.setPatient(patientLoaded);
        healthInsurance.setHealthInsuranceFee(healthInsuranceData.getHealthInsuranceFee());
        healthInsurance.setMonth(healthInsuranceData.getMonth());

        return healthInsuranceAdapter.convertHealthInsuranceEntityToDto(healthInsuranceRepository.save(healthInsurance));

    }


    public HealthInsuranceDto getHealthInsurance(Long healthInsuranceId) throws HealthInsuranceNotFoundException {

        HealthInsurance healthInsuranceLoaded = healthInsuranceRepository.findById(healthInsuranceId)
                .orElseThrow(() -> new HealthInsuranceNotFoundException("No such a health insurance found"));
        return healthInsuranceAdapter.convertHealthInsuranceEntityToDto(healthInsuranceLoaded);

    }


    public List<HealthInsuranceDto> getAllHealthInsurances() {

        return healthInsuranceRepository.findAll().stream().sorted(Comparator.comparing(HealthInsurance::getPatient)
                        .thenComparing(HealthInsurance::getMonth))
                .map(healthInsuranceAdapter::convertHealthInsuranceEntityToDto)
                .collect(Collectors.toList());

    }


    public HealthInsuranceDto updateHealthInsurance(Long healthInsuranceId, HealthInsuranceData healthInsuranceData)
            throws PatientNotFoundException, HealthInsuranceNotFoundException {

        Patient patientLoaded = patientRepository
                .findByEgn(healthInsuranceData.getPatient().getEgn())
                .orElseThrow(() -> new PatientNotFoundException("No such patient found"));

        HealthInsurance healthInsuranceLoaded = healthInsuranceRepository
                .findById(healthInsuranceId).orElseThrow(() -> new HealthInsuranceNotFoundException("No such a health insurance found"));

        healthInsuranceLoaded.setPatient(patientLoaded);
        healthInsuranceLoaded.setHealthInsuranceFee(healthInsuranceData.getHealthInsuranceFee());
        healthInsuranceLoaded.setMonth(healthInsuranceData.getMonth());

        return healthInsuranceAdapter.convertHealthInsuranceEntityToDto(healthInsuranceRepository
                .save(healthInsuranceLoaded));

    }


    public void deleteHealthInsurance(Long id) {
        healthInsuranceRepository.deleteById(id);
    }

    public List<HealthInsuranceDto> getHealthInsurancesByPatientNameAndEgn(PatientData patientData)
            throws PatientNotFoundException, HealthInsuranceNotFoundException {

        Patient patientLoaded = patientRepository.findByEgn(patientData.getEgn())
                .orElseThrow(() -> new PatientNotFoundException("Not such a patient found"));

        List<HealthInsurance> healthInsurancesLoaded = healthInsuranceRepository
                .findByPatientEgn(patientLoaded.getEgn())
                .orElseThrow(() -> new HealthInsuranceNotFoundException("No such health insurance found"));

        List<HealthInsurance> lessThanSixMonths = new ArrayList<HealthInsurance>();
        for (HealthInsurance healthInsurance : healthInsurancesLoaded) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(healthInsurance.getMonth());

            int numberOfMonth = calendar.get(Calendar.MONTH);
            int realMonthNow = LocalDate.now().getMonth().getValue() < numberOfMonth ?
                    LocalDate.now().getMonth().getValue() + 12 : LocalDate.now().getMonth().getValue();

            if (realMonthNow - numberOfMonth <= 6) {
                lessThanSixMonths.add(healthInsurance);
            }
        }

        return lessThanSixMonths.stream().sorted(Comparator.comparing(HealthInsurance::getMonth))
                .map(healthInsuranceAdapter::convertHealthInsuranceEntityToDto)
                .collect(Collectors.toList());

    }

}
