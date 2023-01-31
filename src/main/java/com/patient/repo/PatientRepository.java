package com.patient.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.patient.model.Patient;

/**
 * 
 * @author BK106402 Repository class which fetch data from the database and its
 *         had inbuilt method for supporting crud methods
 *
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	@Query("select case when count(p)> 0 then true else false end from Patient p where patient_id = ?1")
	Boolean isPateintExistsById(Long id);

	@Query("select case when count(p)> 0 then true else false end from Patient p where patient_name = ?1")
	Boolean isPateintExistsByName(String name);

	Optional<Patient> findByPatientName(String patientName);
}
