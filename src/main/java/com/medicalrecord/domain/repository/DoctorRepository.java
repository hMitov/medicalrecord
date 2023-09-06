package com.medicalrecord.domain.repository;

import com.medicalrecord.domain.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByEgn(String egn);

    Optional<List<Doctor>> findByFirstNameAndLastNameAndWorkingPlace(String firstName, String lastName, String workingPlace);

}
