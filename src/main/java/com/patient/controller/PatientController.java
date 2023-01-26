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

import com.patient.model.Patient;
import com.patient.service.PatientService;

@RestController
@RequestMapping("api/patient")
public class PatientController {

	@Autowired
	PatientService patientService;

	@GetMapping
	public ResponseEntity<List<Patient>> getAllPatients() {
		List<Patient> patientList = patientService.getAllPatients();
		return new ResponseEntity<>(patientList, HttpStatus.OK);
	}

	@GetMapping("id/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) {
		Patient patient = patientService.getPatientById(id);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	@GetMapping("name/{name}")
	public ResponseEntity<Patient> getPatientByName(@PathVariable("name") String name) {
		Patient patient = patientService.getPatientByName(name);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
		Patient createdPatient = patientService.saveOrUpdate(patient);
		return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient) {
		patient.setPatientId(id);
		Patient updatedPatient = patientService.updatePatient(patient);
		return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePatient(@PathVariable("id") Long id) {
		patientService.deletePatient(id);
		return new ResponseEntity<>("Patient successfully deleted!", HttpStatus.OK);
	}

}
