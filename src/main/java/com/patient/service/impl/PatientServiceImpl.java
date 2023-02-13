package com.patient.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.constants.ApplicationConstants;
import com.patient.exception.PatientIdNotFoundException;
import com.patient.model.Patient;
import com.patient.repo.PatientRepository;
import com.patient.service.PatientService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author BK106402 Service layer of Patient application which had reference of
 *         DB repoistory and all methods related with CRUD activity
 */
@Slf4j
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
		log.info("creating patient");
		try {
			Patient newPatient = pateintRepository.save(patient);
			return newPatient;
		} catch (Exception ex) {
			throw new PatientIdNotFoundException(ApplicationConstants.ERROR_MESSAGE_603);
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
		log.info("Update patient by patientId : {} ", patient_Id);
		Patient updatedPatient;
		Patient existingPatient = pateintRepository.findById(patient_Id).get();
		if (existingPatient != null) {
			existingPatient.setPatient_id(patient_Id);
			existingPatient.setPatientName(patient.getPatientName());
			existingPatient.setDateOfBirth(patient.getDateOfBirth());
			existingPatient.setGenderCode(patient.getGenderCode());
			existingPatient.setAddressList(patient.getAddressList());
			existingPatient.setTelephoneList(patient.getTelephoneList());
			updatedPatient = pateintRepository.save(existingPatient);
			// return updatedPatient;
		} else {
			throw new NoSuchElementException("No value present");
			// return null;
		}
		return updatedPatient;
	}

	/**
	 * This method delete Patient records in Database when user pass a particular
	 * patient id
	 * 
	 * @return nothing
	 */
	@Override
	public String deletePatient(Long patient_id) {
		log.info("delete method of Patient service impl");
		Boolean isPatientExist = pateintRepository.existsById(patient_id);
		if (!isPatientExist) {
			log.error("patientId : {} does not exist to delete", patient_id);
			throw new PatientIdNotFoundException("Patient Id not found in db with given patient id = " + patient_id);
		}
		pateintRepository.deleteById(patient_id);
		return "Patient successfully deleted with patient id = " + patient_id;
	}

	/**
	 * This method retrieve all Patient records from Database
	 * 
	 * @return List of Patients
	 */

	@Override
	public List<Patient> getPatients() {
		log.info("calling getPatient methods ");
		List<Patient> patientList = pateintRepository.findAll();
		if (patientList.isEmpty()) {
			log.info("No record exists in Patient List");
			throw new PatientIdNotFoundException(ApplicationConstants.ERROR_MESSAGE_607);
		}
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
		log.info("retreiving patient  by its patientId : {}", patientId);
		return pateintRepository.findById(patientId).get();
	}

	/**
	 * This method retrieve a Patient records from Database when user pass a
	 * particular name
	 * 
	 * @return Patient records
	 */
	@Override
	public List<Patient> getPatientByName(String patientName) {
		log.info("retreiving patient  by its name : {}", patientName);
		return pateintRepository.findByPatientName(patientName);
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

}
