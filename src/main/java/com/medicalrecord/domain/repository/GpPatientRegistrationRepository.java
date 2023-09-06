package com.medicalrecord.domain.repository;

import com.medicalrecord.domain.entity.GpPatientRegistration;
import com.medicalrecord.domain.entity.Doctor;
import com.medicalrecord.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GpPatientRegistrationRepository extends JpaRepository<GpPatientRegistration, Long> {

    Optional<GpPatientRegistration> findByPatientAndDoctor(Patient patient, Doctor doctor);

    Optional<List<GpPatientRegistration>> findByPatientEgn(String egn);

}
