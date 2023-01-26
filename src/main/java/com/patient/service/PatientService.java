package com.patient.service;

import java.util.List;

import com.patient.model.Patient;

public interface PatientService {

	List<Patient> getAllPatients();

	Patient getPatientByName(String name);

	Patient getPatientById(Long id);

	Patient saveOrUpdate(Patient patient);

	Patient updatePatient(Patient patient);

	void deletePatient(Long patientId);

}
