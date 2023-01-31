package com.patient.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.exception.BusinessException;
import com.patient.exception.PatientIdNotFoundException;
import com.patient.model.Patient;
import com.patient.repo.PatientRepository;
import com.patient.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository pateintRepository;

	@Override
	public Patient updatePatient(Patient patient) {
		Patient existingPatient = pateintRepository.findById(patient.getPatient_id()).get();
		existingPatient.setPatientName(patient.getPatientName());
		existingPatient.setDateOfBirth(patient.getDateOfBirth());
		existingPatient.setGenderCode(patient.getGenderCode());
		existingPatient.setAddressList(patient.getAddressList());
		existingPatient.setTelephoneList(patient.getTelephoneList());
		Patient updatedPatient = pateintRepository.save(existingPatient);
		return updatedPatient;
	}

	@Override
	public Patient CreatePatient(Patient patient) {
		if (patient.getPatientName().isEmpty() || patient.getPatientName().length() == 0) {
			throw new BusinessException("601", "patient name is empty");
		}
		try {
			Patient newPatient = pateintRepository.save(patient);
			return newPatient;
		} catch (IllegalArgumentException ex) {
			throw new BusinessException("602", "given patient is null" + ex.getMessage());
		} catch (Exception ex) {
			throw new BusinessException("603",
					"something went woring in service layer while saving the patient" + ex.getMessage());
		}
	}

	@Override
	public void deletePatient(Long patientId) {
		try {
			pateintRepository.deleteById(patientId);
		} catch (IllegalArgumentException ex) {
			throw new BusinessException("604",
					"given patient id is null send soeme id to be deleted" + ex.getMessage());
		} catch (Exception ex) {
			throw new BusinessException("605",
					"something went woring in service layer while deleting the patient" + ex.getMessage());
		}

	}

	@Override
	public List<Patient> getPatients() {
		List<Patient> patientList = null;
		try {
			patientList = pateintRepository.findAll();
		} catch (Exception ex) {
			throw new BusinessException("606",
					"something went woring in service layer while deleting the patient" + ex.getMessage());
		}
		if (patientList.isEmpty())
			throw new BusinessException("607", "patient List is empty");
		return patientList;
	}

	@Override
	public Patient getPatientById(Long patientId) {
		try {
			return pateintRepository.findById(patientId).get();
		} catch (IllegalArgumentException ex) {
			throw new BusinessException("607",
					"given patient id is null send soeme id to be searched");
		} catch (NoSuchElementException ex) {
			throw new BusinessException("608", "given patient id is does not exist in DB");
		} catch (Exception ex) {
			throw new BusinessException("609",
					"something went woring in service layer while searching  the patient by id " + ex.getMessage());
		}

	}

	@Override
	public Patient getPatientByName(String patientName) {
		try {
			return pateintRepository.findByPatientName(patientName).get();
		} catch (IllegalArgumentException ex) {
			throw new BusinessException("610",
					"given patient Name is null send soeme id to be searched" + ex.getMessage());
		} catch (NoSuchElementException ex) {
			throw new BusinessException("611", "given patient Name is does not exist in DB" + ex.getMessage());
		} catch (Exception ex) {
			throw new BusinessException("612",
					"something went woring in service layer while while searching  the patient by name"
							+ ex.getMessage());
		}
	}

	@Override
	public boolean isPateintExistsById(Long id) {
		return pateintRepository.isPateintExistsById(id);
	}

	@Override
	public boolean isPateintExistsByName(String name) {
		return pateintRepository.isPateintExistsByName(name);
	}

	public PatientServiceImpl(PatientRepository pateintRepository) {
		this.pateintRepository = pateintRepository;
	}

}
