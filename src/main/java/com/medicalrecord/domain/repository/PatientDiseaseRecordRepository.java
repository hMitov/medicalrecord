package com.medicalrecord.domain.repository;

import com.medicalrecord.domain.entity.Doctor;
import com.medicalrecord.domain.entity.Patient;
import com.medicalrecord.domain.entity.PatientDiseaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PatientDiseaseRecordRepository extends JpaRepository<PatientDiseaseRecord, Long> {

    Optional<PatientDiseaseRecord> findByPatientAndDoctorAndVisitingTime(Patient patient,
                                                                         Doctor doctor,
                                                                         Date localDateTime);

    Optional<List<PatientDiseaseRecord>> findByPatientEgn(String egn);
}
