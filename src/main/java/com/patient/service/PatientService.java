package com.patient.service;

import java.util.List;

import com.patient.model.Patient;

/**
 * 
 * @author BK106402 Interface for Service class
 *
 */
public interface PatientService {

	Patient createPatient(Patient patient);

	Patient updatePatient(Patient patient, Long patient_Id);

	Patient getPatientByName(String patientName);

	Patient getPatientById(Long id);

	List<Patient> getPatients();

	String deletePatient(Long patientId);

	boolean isPateintExistsById(Long id);

	boolean isPateintExistsByName(String name);

}
