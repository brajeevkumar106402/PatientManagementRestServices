package com.patient.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.model.Patient;
import com.patient.repo.PatientRepository;
import com.patient.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository pateintRepository;

	@Override
	public List<Patient> getAllPatients() {
		List<Patient> patientList = new ArrayList<>();
		pateintRepository.findAll().forEach(patient -> patientList.add(patient));
		return patientList;
	}

	@Override
	public Patient getPatientById(Long patientId) {
		Optional<Patient> optionalPatient = pateintRepository.findById(patientId);
		return optionalPatient.get();
	}

	@Override
	public Patient getPatientByName(String patientName) {
		Optional<Patient> optionalPatient = pateintRepository.findByPatientName(patientName);
		return optionalPatient.get();
	}

	@Override
	public Patient updatePatient(Patient patient) {
		Patient existingPatient = pateintRepository.findById(patient.getPatientId()).get();
		existingPatient.setPatientName(patient.getPatientName());
		existingPatient.setDateOfBirth(patient.getDateOfBirth());
		existingPatient.setGenderCode(patient.getGenderCode());
		existingPatient.setAddressList(patient.getAddressList());
		existingPatient.setTelephoneList(patient.getTelephoneList());
		Patient updatedPatient = pateintRepository.save(existingPatient);
		return updatedPatient;
	}

	@Override
	public Patient saveOrUpdate(Patient patient) {
		return pateintRepository.save(patient);
	}

	@Override
	public void deletePatient(Long patientId) {
		pateintRepository.deleteById(patientId);
	}

}
