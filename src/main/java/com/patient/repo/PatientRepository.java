package com.patient.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	Optional<Patient> findByPatientName(String patientName);
}
