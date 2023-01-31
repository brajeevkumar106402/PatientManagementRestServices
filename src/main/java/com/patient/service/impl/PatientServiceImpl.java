package com.patient.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.constants.ApplicationConstants;
import com.patient.exception.BusinessException;
import com.patient.model.Patient;
import com.patient.repo.PatientRepository;
import com.patient.service.PatientService;

/**
 * 
 * @author BK106402 Service layer of Patient application which had reference of
 *         DB repoistory and all methods related with CRUD activity
 */
@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	PatientRepository pateintRepository;

	/**
	 * This method creates Patient in Database
	 * 
	 * @return Patient as response
	 */
	@Override
	public Patient createPatient(Patient patient) {
		try {
			Patient newPatient = pateintRepository.save(patient);
			return newPatient;
		} catch (IllegalArgumentException ex) {
			throw new BusinessException(ApplicationConstants.CODE_602, ApplicationConstants.ERROR_MESSAGE_602);
		} catch (Exception ex) {
			throw new BusinessException(ApplicationConstants.CODE_603, ApplicationConstants.ERROR_MESSAGE_603);
		}
	}

	/**
	 * This method updated Patient in Database when user pass a particular patient
	 * id
	 * 
	 * @return Patient as response
	 */
	@Override
	public Patient updatePatient(Patient patient, Long patient_Id) {
		Patient existingPatient = pateintRepository.findById(patient_Id).get();
		existingPatient.setPatientName(patient.getPatientName());
		existingPatient.setDateOfBirth(patient.getDateOfBirth());
		existingPatient.setGenderCode(patient.getGenderCode());
		existingPatient.setAddressList(patient.getAddressList());
		existingPatient.setTelephoneList(patient.getTelephoneList());
		Patient updatedPatient = pateintRepository.save(existingPatient);
		return updatedPatient;
	}

	/**
	 * This method delete Patient records in Database when user pass a particular
	 * patient id
	 * 
	 * @return nothing
	 */
	@Override
	public void deletePatient(Long patientId) {
		try {
			pateintRepository.deleteById(patientId);
		} catch (IllegalArgumentException ex) {
			throw new BusinessException(ApplicationConstants.CODE_604, ApplicationConstants.ERROR_MESSAGE_604);
		} catch (Exception ex) {
			throw new BusinessException(ApplicationConstants.CODE_605, ApplicationConstants.ERROR_MESSAGE_605);
		}

	}

	/**
	 * This method retrieve all Patient records from Database
	 * 
	 * @return List of Patients
	 */

	@Override
	public List<Patient> getPatients() {
		List<Patient> patientList = null;
		try {
			patientList = pateintRepository.findAll();
		} catch (Exception ex) {
			throw new BusinessException(ApplicationConstants.CODE_606, ApplicationConstants.ERROR_MESSAGE_606);
		}
		if (patientList.isEmpty())
			throw new BusinessException(ApplicationConstants.CODE_607, ApplicationConstants.ERROR_MESSAGE_607);
		return patientList;
	}

	/**
	 * This method retrieve a Patient records from Database when user pass a
	 * particular patient id
	 * 
	 * @return Patient records
	 */
	@Override
	public Patient getPatientById(Long patientId) {
		try {
			return pateintRepository.findById(patientId).get();
		} catch (IllegalArgumentException ex) {
			throw new BusinessException(ApplicationConstants.CODE_608, ApplicationConstants.ERROR_MESSAGE_608);
		} catch (NoSuchElementException ex) {
			throw new BusinessException(ApplicationConstants.CODE_609, ApplicationConstants.ERROR_MESSAGE_609);
		} catch (Exception ex) {
			throw new BusinessException(ApplicationConstants.CODE_610, ApplicationConstants.ERROR_MESSAGE_610);
		}
	}

	/**
	 * This method retrieve a Patient records from Database when user pass a
	 * particular name
	 * 
	 * @return Patient records
	 */
	@Override
	public Patient getPatientByName(String patientName) {
		try {
			return pateintRepository.findByPatientName(patientName).get();
		} catch (IllegalArgumentException ex) {
			throw new BusinessException(ApplicationConstants.CODE_611, ApplicationConstants.ERROR_MESSAGE_611);
		} catch (NoSuchElementException ex) {
			throw new BusinessException(ApplicationConstants.CODE_612, ApplicationConstants.ERROR_MESSAGE_612);
		} catch (Exception ex) {
			throw new BusinessException(ApplicationConstants.CODE_613, ApplicationConstants.ERROR_MESSAGE_613);
		}
	}

	/**
	 * This method checks a particular Id in Database when user pass a particular id
	 * 
	 * @return either true or false
	 */
	@Override
	public boolean isPateintExistsById(Long id) {
		return pateintRepository.isPateintExistsById(id);
	}

	/**
	 * This method checks a particular name in Database when user pass a particular
	 * name
	 * 
	 * @return either true or false
	 */
	@Override
	public boolean isPateintExistsByName(String name) {
		return pateintRepository.isPateintExistsByName(name);
	}

	/*
	 * Constructor of Service class
	 */
	public PatientServiceImpl(PatientRepository pateintRepository) {
		this.pateintRepository = pateintRepository;
	}

}
