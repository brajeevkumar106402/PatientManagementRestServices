package com.patient.service;

import java.util.List;

import com.patient.model.Patient;

public interface PatientService {
	
	Patient CreatePatient(Patient patient);
	
	Patient updatePatient(Patient patient);
	
	Patient getPatientByName(String patientName);

	Patient getPatientById(Long id);

	List<Patient> getPatients();	

	void deletePatient(Long patientId);

	boolean isPateintExistsById(Long id);

	boolean isPateintExistsByName(String name);
	
//	boolean isPatientExist(Long patientId);

}
