package com.medicalrecord.domain.repository;

import com.medicalrecord.domain.entity.HealthInsurance;
import com.medicalrecord.domain.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface HealthInsuranceRepository extends JpaRepository<HealthInsurance, Long> {

    Optional<HealthInsurance> findByPatientAndMonth(Patient patient, Date month);

    Optional<List<HealthInsurance>> findByPatientEgn(String egn);

    Optional<HealthInsurance> findById(Long id);

}
