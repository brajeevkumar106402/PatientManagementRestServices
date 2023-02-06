package com.patient.controller;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.model.Address;
import com.patient.model.Patient;
import com.patient.model.Telephone;
import com.patient.service.PatientService;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {
	
	  @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private PatientService patientService;
	    

	    @InjectMocks
	    private PatientController patientController;

	    @Autowired
	    private ObjectMapper objectMapper;
	    
	    
	    private static Patient patient;
	    private static Patient patient2;
	    
	    
	    @BeforeAll
	   static void setUp() {
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

			 patient = Patient.builder().patient_id(1L).patientName("brajeev").genderCode("MALE")
					.dateOfBirth("01-03-1984").addressList(addressListtemp).telephoneList(telephoneListtemp).build();
			 patient2 = Patient.builder().patient_id(2L).patientName("rajeev").genderCode("MALE")
						.dateOfBirth("01-03-1978").addressList(addressListtemp).telephoneList(telephoneListtemp).build();
	    
	    }
	    // JUnit test for Get All employees REST API
	    
	    @Test
	    public void givenPatientObject_whenCreatePatient_thenReturnSavedPatient() throws Exception{

	        // given - precondition or setup
	        
	        given(patientService.createPatient(any(Patient.class)))
	                .willAnswer((invocation)-> invocation.getArgument(0));

	        // when - action or behaviour that we are going test
	        ResultActions response = mockMvc.perform(post("/api/patient")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(patient)));

	        // then - verify the result or output using assert statements
	        response.andDo(print()).
	                andExpect(status().isCreated())	;           
	           

	    }      
	    
	    
	    @Test
	    void createPatientTest() {
	        when(patientService.createPatient(any(Patient.class))).thenReturn(patient);
	        ResponseEntity<Patient> actual = patientController.createPatient(patient);
	        assertEquals(patient.getPatient_id(), actual.getBody().getPatient_id());
	        assertEquals(patient.getPatientName(), actual.getBody().getPatientName());       
	        assertEquals(patient.getGenderCode(), actual.getBody().getGenderCode());	      
	        assertEquals(patient.getDateOfBirth(), actual.getBody().getDateOfBirth());
	        assertEquals(patient.getAddressList(), actual.getBody().getAddressList());
	        assertEquals(patient.getTelephoneList(), actual.getBody().getTelephoneList());
	        
	        assertEquals(patient.getAddressList().size(), actual.getBody().getAddressList().size());
	        assertEquals(patient.getAddressList().get(0).getId(), actual.getBody().getAddressList().get(0).getId());
	        assertEquals(patient.getAddressList().get(0).getAddressType(), actual.getBody().getAddressList().get(0).getAddressType());	        
	        assertEquals(patient.getAddressList().get(0).getAddressLine1(), actual.getBody().getAddressList().get(0).getAddressLine1());
	        assertEquals(patient.getAddressList().get(0).getAddressLine2(), actual.getBody().getAddressList().get(0).getAddressLine2());
	        assertEquals(patient.getAddressList().get(0).getCity(), actual.getBody().getAddressList().get(0).getCity());
	        assertEquals(patient.getAddressList().get(0).getState(), actual.getBody().getAddressList().get(0).getState());
	        assertEquals(patient.getAddressList().get(0).getPostalCode(), actual.getBody().getAddressList().get(0).getPostalCode());
	        
	        assertEquals(patient.getTelephoneList().size(), actual.getBody().getTelephoneList().size());
	        assertEquals(patient.getTelephoneList().get(0).getId(), actual.getBody().getTelephoneList().get(0).getId());
	        assertEquals(patient.getTelephoneList().get(0).getTelephoneType(), actual.getBody().getTelephoneList().get(0).getTelephoneType());
	        assertEquals(patient.getTelephoneList().get(0).getTelephoneNumber(), actual.getBody().getTelephoneList().get(0).getTelephoneNumber());
	        assertEquals(patient.getTelephoneList().get(0).getTelephonCountryCode(), actual.getBody().getTelephoneList().get(0).getTelephonCountryCode());
	 	   
	    }
	       
	    // JUnit test for Get All employees REST API
	    @Test
	    public void givenListOfPatients_whenGetAllPateintsthenReturnPatientList() throws Exception{
	        // given - precondition or setup
	        List<Patient> listOfPateint = new ArrayList<>();
	        listOfPateint.add(patient);
	        listOfPateint.add(patient2);
	       given(patientService.getPatients()).willReturn(listOfPateint);

	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/api/patient"));

	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print())
	                .andExpect(jsonPath("$.size()",
	                        is(listOfPateint.size())));

	    }
	    
	    // positive scenario - valid Patient id	 JUnit test for GET Patient by id REST API
	    @Test
	    public void givenPateintId_whenGetPatientById_thenReturnPatientObject() throws Exception{
	        // given - precondition or setup
	    	
	       Long pateintId = 1L;
	       given(patientService.isPateintExistsById(any(Long.class))).willReturn(Boolean.TRUE);	       
	       given(patientService.getPatientById(any(Long.class))).willReturn(patient);
	    // when -  action or the behaviour that we are going test
	     ResultActions response = mockMvc.perform(get("/api/patient/id/{id}", pateintId));

	        // then - verify the output
	        response.andExpect(status().isOk());
	    }
	    
	    // negative scenario - valid employee id
	    // JUnit test for GET employee by id REST API
	    @Test
	    public void givenInvalidEmployeeId_whenGetEmployeeById_thenReturnEmpty() throws Exception{
	        // given - precondition or setup
	    	 Long pateintId = 1L;
	    	 given(patientService.isPateintExistsById(pateintId)).willReturn(Boolean.FALSE);
	    	  given(patientService.getPatientById(pateintId)).willReturn(null);
		      //  given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
	       // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/api/patient/id/{id}", pateintId));

	        // then - verify the output
	        response.andExpect(status().isNotFound())
	                .andDo(print());
	        

	    }
	    
	    
	    @Test
	    public void givenPatientName_whenGetPatientName_thenReturnPatientObject() throws Exception{
	        // given - precondition or setup
	    	 String pateintName = "brajeev";
	    	   given(patientService.isPateintExistsByName(pateintName)).willReturn(Boolean.TRUE);	
	       given(patientService.getPatientByName(pateintName)).willReturn(patient);
	    // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/api/patient/name/{name}", pateintName));

	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print());
	         

	    }
	    
	    // negative scenario - valid employee id
	    // JUnit test for GET employee by id REST API
	    @Test
	    public void givenInvalidPatientName_whenGetPatientName_thenReturnEmpty() throws Exception{
	        // given - precondition or setup
	    	 String pateintName = "brajeevkumar";
	    	 given(patientService.isPateintExistsByName(pateintName)).willReturn(Boolean.FALSE);	
		       given(patientService.getPatientByName(pateintName)).willReturn(null);
		      //  given(employeeService.getEmployeeById(employeeId)).willReturn(Optional.empty());
	       // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(get("/api/patient/name/{name}", pateintName));

	        // then - verify the output
	        response.andExpect(status().isNotFound())
	                .andDo(print()).andExpect(content().string(containsString("patient name does not exist")));

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
	    @Test
	    public void givenPatientId_whenDeletePatientId_thenReturn200() throws Exception{
	        // given - precondition or setup
	        long patientId = 1L;
	        Patient savedPatient = Patient.builder().patient_id(1L).patientName("ramu ji").genderCode("MALE")
	    			.dateOfBirth("01-03-1984").build();
	        given(patientService.deletePatient(patientId)).willReturn(null);

	        // when -  action or the behaviour that we are going test
	        ResultActions response = mockMvc.perform(delete("/api/patient/{id}", patientId));

	        // then - verify the output
	        response.andExpect(status().isOk())
	                .andDo(print());
	    }

}
