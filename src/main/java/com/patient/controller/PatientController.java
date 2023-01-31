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
import com.patient.exception.BusinessException;
import com.patient.exception.ControllerException;
import com.patient.model.Patient;
import com.patient.service.PatientService;

@RestController
@RequestMapping("api/patient")
public class PatientController {

	@Autowired
	PatientService patientService;

	@GetMapping
	public ResponseEntity<List<Patient>> getAllPatients() {
		List<Patient> patientList = patientService.getPatients();
		return new ResponseEntity<>(patientList, HttpStatus.OK);
	}

	@GetMapping("id/{id}")
	public ResponseEntity<Object> getPatientById(@PathVariable("id") Long id) {
		boolean isPatientExist = patientService.isPateintExistsById(id);
		if (!isPatientExist) {
			return new ResponseEntity<>(new String(ApplicationConstants.PATIENT_ID_NOT_FOUND), HttpStatus.NOT_FOUND);
		}
		Patient patient = patientService.getPatientById(id);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	@GetMapping("name/{name}")
	public ResponseEntity<Object> getPatientByName(@PathVariable("name") String name) {
		boolean isPatientNameExist = patientService.isPateintExistsByName(name);
		if (!isPatientNameExist) {
			return new ResponseEntity<>(new String(ApplicationConstants.PATIENT_NAME_NOT_FOUND), HttpStatus.NOT_FOUND);
		}
		Patient patient = patientService.getPatientByName(name);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
		try {
			Patient newPatient = patientService.CreatePatient(patient);
			return new ResponseEntity<>(
					new String(ApplicationConstants.SUCCESSFUL_PATIENT_MESSAGE + newPatient.getPatient_id()),
					HttpStatus.CREATED);
		} catch (BusinessException e) {
			ControllerException ce = new ControllerException(e.getErrorCode(), e.getErrorMessage());
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			ControllerException ce = new ControllerException("611", "something went woring in controller");
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) {
		Patient updatePatient = patientService.CreatePatient(patient);
		return new ResponseEntity<Patient>(updatePatient, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {
		patientService.deletePatient(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

}
