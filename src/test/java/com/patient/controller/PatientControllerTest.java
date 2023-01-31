package com.patient.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.patient.model.Address;
import com.patient.model.Patient;
import com.patient.model.Telephone;
import com.patient.repo.PatientRepository;

//@WebMvcTest(PatientControllerTest.class)
@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {
	
	@InjectMocks
	PatientController PatientController;
	
	@Mock
	PatientRepository PatientRepository;

	
/*	@Autowired
	private MockMvc mvc;
	


	@Test
	public void getAllPatientsTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/api/patient").accept(MediaType.APPLICATION_JSON)).				
			      andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.patient").exists())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.patient[*].").isNotEmpty());				
				
		
	}
	*/
	
	@Test
	public void testFindAll() 
	{
		// given
		
		 List<Address> addressListtemp = new ArrayList<>();
			Address adddress1 = Address.builder().id(103).addressType("home").addressLine1("House no -85")
					.addressLine2("Gali no 1B").city("Chapraula").state("Uttar Pradesh").postalCode("201009")
					.country("india").build();
			Address adddress2 = Address.builder().id(104).addressType("office").addressLine1("House no -85")
					.addressLine2("Gali no 1B").city("Chapraula").state("Uttar Pradesh").postalCode("201009")
					.country("india").build();
			addressListtemp.add(adddress1);
			addressListtemp.add(adddress2);
			List<Telephone> telephoneListtemp = new ArrayList<>();
			Telephone tel1 = Telephone.builder().id(1).telephoneType("office").telephoneNumber("9211642430")
					.telephonCountryCode("011").build();
			Telephone tel2 = Telephone.builder().id(2).telephoneType("office").telephoneNumber("9211642430")
					.telephonCountryCode("011").build();
			telephoneListtemp.add(tel1);
			telephoneListtemp.add(tel2);

			Patient patient1 = Patient.builder().patient_id(1L).patientName("ramu").genderCode("MALE")
					.dateOfBirth("01-03-1984").addressList(addressListtemp).telephoneList(telephoneListtemp).build();
	 
			
			Patient patient2 = Patient.builder().patient_id(2L).patientName("ramu kaka").genderCode("MALE")
					.dateOfBirth("01-03-1984").addressList(addressListtemp).telephoneList(telephoneListtemp).build();
	 
		
		List<Patient> list = new ArrayList<Patient>();
		list.addAll(Arrays.asList(patient1, patient2));

	//	when(PatientRepository.findAll()).thenReturn(list);

		// when
		List<Patient> result = (List<Patient>) PatientController.getAllPatients();

		// then
		assertThat(result.size()).isEqualTo(2);
		
		assertThat(result.get(0).getPatientName()).isEqualTo(patient1.getPatientName());
		assertThat(result.get(1).getPatientName()).isEqualTo(patient2.getPatientName());
	}

	
	
	/*
	
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
		} 
		catch (Exception e) {
			ControllerException ce = new ControllerException(ApplicationConstants.CODE_611, ApplicationConstants.ERROR_MESSAGE_611);
			return new ResponseEntity<ControllerException>(ce, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) {
		Patient updatePatient = patientService.updatePatient(patient);
		return new ResponseEntity<Patient>(updatePatient, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {
		patientService.deletePatient(id);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
*/
}
