package com.medicalrecord.domain.service;

import com.medicalrecord.domain.entity.Specialty;
import org.springframework.stereotype.Service;

@Service
public class SpecialtyService {
    public Specialty[] getSpecialties() {
        return Specialty.values();
    }
}
