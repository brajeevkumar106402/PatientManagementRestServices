package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.constants.ApplicationConstants;
import com.patient.exception.PatientIdNotFoundException;
import com.patient.model.Patient;
import com.patient.service.PatientService;

@RestController
@RequestMapping("api/patient")
/**
 * 
 * @author BK106402 Controller class for managing the Patient related activity
 *         like adding Patient,Removing patient, updating patient and find the
 *         patient by id and its name *
 */
public class PatientController {

	@Autowired
	PatientService patientService;

	/**
	 * This method retrieves all patients
	 * 
	 * @return List of Patients
	 */
	@GetMapping
	public ResponseEntity<List<Patient>> getAllPatients() {
		List<Patient> patientList = patientService.getPatients();
		return new ResponseEntity<>(patientList, HttpStatus.OK);
	}

	/**
	 * This method retrieves patients based on id
	 * 
	 * @return Patient
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<Object> getPatientById(@PathVariable("id") Long id) {
		boolean isPatientExist = patientService.isPateintExistsById(id);
		if (!isPatientExist) {
			return new ResponseEntity<>(new String(ApplicationConstants.PATIENT_ID_NOT_FOUND), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
	}

	/**
	 * This method retrieves patients based on its name
	 * 
	 * @return Patient
	 */
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Patient>> getPatientByName(@PathVariable("name") String name) {
		//boolean isPatientNameExist = patientService.isPateintExistsByName(name);
	////	if (!isPatientNameExist) {
			//return new ResponseEntity<>(new String(ApplicationConstants.PATIENT_NAME_NOT_FOUND), HttpStatus.NOT_FOUND);
	//	}
		return new ResponseEntity<>(patientService.getPatientByName(name), HttpStatus.OK);
	}

	/**
	 * This method create patients
	 * 
	 * @return PatientId when Patient is successfully created otherwise throws
	 *         exception
	 */
	@PostMapping
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
	//	try {
			//Patient newPatient = patientService.createPatient(patient);
			return new ResponseEntity<>(
					patientService.createPatient(patient),
					HttpStatus.CREATED);
		//} catch (Exception e) {
		//	PatientIdNotFoundException ce = new PatientIdNotFoundException(ApplicationConstants.ERROR_MESSAGE_611);
		//	return new ResponseEntity<PatientIdNotFoundException>(ce, HttpStatus.BAD_REQUEST);
		//}
	}

	/**
	 * This method update patients details based on patient_id
	 * 
	 * @return updated Patient details when Patient is successfully created
	 *         otherwise throws exception
	 */
	@PutMapping("{id}")
	public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient, @PathVariable("id") Long patient_Id) {
		return new ResponseEntity<Patient>(patientService.updatePatient(patient, patient_Id), HttpStatus.OK);
	}

	/**
	 * This method delete patient based on Id
	 * 
	 * @return successful message if Patient is successfully deleted or otherwise
	 *         throws exception with unsuccessful message
	 */

	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePatient(@PathVariable("id") Long id) {
		return new ResponseEntity<>(patientService.deletePatient(id), HttpStatus.OK);
	}

}
