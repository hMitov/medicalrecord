package com.medicalrecord.domain.repository;

import com.medicalrecord.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByEgn(String egn);

    Optional<List<Patient>> findPatientsByEgn(String egn);
}
