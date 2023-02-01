package com.patient.controller;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.model.Patient;
import com.patient.service.PatientService;

@WebMvcTest
public class PatientControllerTest {
	
	  @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private PatientService patientService;

	    @Autowired
	    private ObjectMapper objectMapper;
	    
	    @Test
	    public void givenPatientObject_whenCreatePatient_thenReturnSavedPatient() throws Exception{

	        // given - precondition or setup
	        
	         Patient patient =      Patient.builder().patient_id(1L).patientName("ramu").genderCode("MALE")
			.dateOfBirth("01-03-1984").build();
	        given(patientService.createPatient(any(Patient.class)))
	                .willAnswer((invocation)-> invocation.getArgument(0));

	        // when - action or behaviour that we are going test
	        ResultActions response = mockMvc.perform(post("/api/patient")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(patient)));

	        // then - verify the result or output using assert statements
	        response.andDo(print()).
	                andExpect(status().isCreated())	            
	                .andExpect(content().string(containsString("Patient is successfully created with Patient ID")));
	            

	    }
	    // JUnit test for Get All employees REST API
	    @Test
	    public void givenListOfPatients_whenGetAllPateintsthenReturnPatientList() throws Exception{
	        // given - precondition or setup
	        List<Patient> listOfPateint = new ArrayList<>();
	        listOfPateint.add(Patient.builder().patient_id(1L).patientName("ramu").genderCode("MALE")
	    			.dateOfBirth("01-03-1984").build());
	        listOfPateint.add(Patient.builder().patient_id(1L).patientName("ramu").genderCode("MALE")
	    			.dateOfBirth("01-03-1984").build());
	        given(patientService.getPatients()).willReturn(listOfPateint);

	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/api/patient"));

	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(jsonPath("$.size()",
	                        is(listOfPateint.size())));

	    }
	    
	    // positive scenario - valid Patient id
	    // JUnit test for GET Patient by id REST API
	    @Test
	    public void givenPateintId_whenGetPatientById_thenReturnPatientObject() throws Exception{
	        // given - precondition or setup
	        long pateintId = 1L;
	        Patient patient = Patient.builder().patient_id(1L).patientName("ramu ji").genderCode("MALE")
	    			.dateOfBirth("01-03-1984").build();
	        given(patientService.getPatientById(pateintId)).willReturn(patient);
	    // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/api/patient/id/{id}", pateintId));

	        // then - verify the output
	        response.andExpect(status().isNotFound())
	                .andDo(print())
	                .andExpect(content().string(containsString("patient id does not exist")));
		            
	             /*   .andExpect(jsonPath("$.Patient_id", is(patient.getPatient_id())))
	                .andExpect(jsonPath("$.GenderCode", is(patient.getGenderCode())))
	                .andExpect(jsonPath("$.DateOfBirth", is(patient.getDateOfBirth())));*/

	    }
	    
	    // negative scenario - valid employee id
	    // JUnit test for GET employee by id REST API
	    @Test
	    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception{
	        // given - precondition or setup
	    	 Long pateintId = 1L;
		        Patient patient = Patient.builder().patient_id(1L).patientName("ramu ji").genderCode("MALE")
		    			.dateOfBirth("01-03-1984").build();
		        given(patientService.getPatientById(pateintId)).willReturn(null);
		      //  given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
	       // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/api/employees/{id}", pateintId));

	        // then - verify the output
	        response.andExpect(status().isNotFound())
	                .andDo(print());

	    }
	    
	    // JUnit test for update employee REST API - positive scenario
	    @Test
	    public void givenUpdatedPateint_whenUpdatePateint_thenReturn200() throws Exception{
	        // given - precondition or setup
	       
	        Long pateintId = 1L;
	        Patient savedPatient = Patient.builder().patient_id(1L).patientName("ramu ji").genderCode("MALE")
	    			.dateOfBirth("01-03-1984").build();
	        Patient updatedPatient = Patient.builder().patient_id(1L).patientName("ramukumar ji").genderCode("MALE")
	    			.dateOfBirth("01-03-1983").build();

	        given(patientService.getPatientById(pateintId)).willReturn(null);
	     //   given(patientService.updatePatient(any(Patient.class),pateintId))
	        given(patientService.updatePatient(any(Patient.class),any(Long.class)))
	                .willAnswer((invocation)-> invocation.getArgument(0));

	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(put("/api/patient/{id}", pateintId)
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(updatedPatient)));

	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print());
	    }
	    
	 // JUnit test for delete patient REST API
	/*    @Test
	    public void givenPatientId_whenDeletePatientId_thenReturn200() throws Exception{
	        // given - precondition or setup
	        long patientId = 1L;
	        willDoNothing().given(patientService).deletePatient(patientId);

	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(delete("/api/patient/{id}", patientId));

	        // then - verify the output
	        response.andExpect(status().isAccepted())
	                .andDo(print());
	    }*/

}
