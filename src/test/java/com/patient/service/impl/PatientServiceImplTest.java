package com.patient.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.patient.model.Address;
import com.patient.model.Patient;
import com.patient.model.Telephone;
import com.patient.repo.PatientRepository;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

	@Mock
	PatientRepository pateintRepository;

	@InjectMocks
	private PatientServiceImpl patientServiceImpl;
	 
	 private Patient patient;
	
	
	 @Test
	    public void whenSavePatient_shouldReturnPatient() {
	        Patient patient = new Patient();
	        patient.setPatientName("Test patient");

	        when(pateintRepository.save(ArgumentMatchers.any(Patient.class))).thenReturn(patient);

	        Patient created = patientServiceImpl.createPatient(patient);

	        assertThat(created.getPatientName()).isSameAs(patient.getPatientName());
	        verify(pateintRepository).save(patient);
	    }
	  
	  @Test
		public void getPatientByIdTest() {
			when(pateintRepository.findById(1L)).thenReturn(createEmployeeStub());
			Patient testedPatient = patientServiceImpl.getPatientById(1L);
			Assertions.assertThat(testedPatient.getPatientName()).isEqualTo("cernerPatient");
		//	assertEquals(testedPatient.getPatientName(), "cernerPatient");

		}

		private Optional<Patient> createEmployeeStub() {
			Patient stubPatient = Patient.builder().patient_id(1L).patientName("cernerPatient").build();
			return Optional.of(stubPatient);
		}
		
		
		@Test
		public void getPatientByNameTest() {
			when(pateintRepository.findByPatientName("cernerPatient")).thenReturn(createEmployeeStub());
			Patient testedPatient = patientServiceImpl.getPatientByName("cernerPatient");
			Assertions.assertThat(testedPatient.getPatient_id()).isEqualTo(1L);
		//	assertEquals(testedPatient.getPatient_id(), new Long(1L));

		}
		
		 @BeforeEach
		    public void setup(){
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

				 patient = Patient.builder().patient_id(1L).patientName("ramu").genderCode("MALE")
						.dateOfBirth("01-03-1984").addressList(addressListtemp).telephoneList(telephoneListtemp).build();
		    }
		 
		 // JUnit test for saveEmployee method
		    @DisplayName("JUnit test for savePatient method")
		    @Test
		    public void givenPatientObject_whenSavePatient_thenReturnPatientObject(){
		        // given - precondition or setup
		     //   given(pateintRepository.findByPatientName(pateint.getPatientName()))
		           //     .willReturn(Optional.empty());

		        given(pateintRepository.save(patient)).willReturn(patient);

		        System.out.println(pateintRepository);
		        System.out.println(patientServiceImpl);

		        // when -  action or the behaviour that we are going test
		        Patient savedPatient = patientServiceImpl.createPatient(patient);

		        System.out.println(savedPatient);
		        // then - verify the output
		        assertThat(savedPatient).isNotNull();
		    }
		
		
		    // JUnit test for saveEmployee method
		 /*   @DisplayName("JUnit test for savePatient method which throws exception")
		    @Test
		    public void givenExistingEmail_whenSaveEmployee_thenThrowsException(){
		        // given - precondition or setup
		       
		        given(pateintRepository.findByPatientName(patient.getPatientName()))
		                .willReturn(Optional.of(patient));

		        System.out.println(pateintRepository);
		        System.out.println(patientServiceImpl);

		        // when -  action or the behaviour that we are going test
		        org.junit.jupiter.api.Assertions.assertThrows(BusinessException.class, () -> {
		        	patientServiceImpl.CreatePatient(patient);
		        });

		        // then
		        verify(pateintRepository, never()).save(any(Patient.class));
		        
		    }*/
		    
		    // JUnit test for getAllEmployees method
		    @DisplayName("JUnit test for getPatients method")
		    @Test
		    public void givenPatientsList_whenGetAllPatients_thenReturnPatientsList(){
		        // given - precondition or setup

		       
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

			Patient	 patient1 = Patient.builder().patient_id(1L).patientName("ramu").genderCode("MALE")
						.dateOfBirth("01-03-1984").addressList(addressListtemp).telephoneList(telephoneListtemp).build();
		   

		        given(pateintRepository.findAll()).willReturn(List.of(patient,patient1));

		        // when -  action or the behaviour that we are going test
		        List<Patient> patientList = patientServiceImpl.getPatients();

		        // then - verify the output
		        assertThat(patientList).isNotNull();
		        assertThat(patientList.size()).isEqualTo(2);
		    }
		    
		/*    // JUnit test for getAllEmployees method
		    @DisplayName("JUnit test for getAllEmployees method (negative scenario)")
		    @Test
		    public void givenEmptyEmployeesList_whenGetAllEmployees_thenReturnEmptyEmployeesList(){
		        // given - precondition or setup

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

				Patient	 patient1 = Patient.builder().patient_id(1L).patientName("ramu").genderCode("MALE")
							.dateOfBirth("01-03-1984").addressList(addressListtemp).telephoneList(telephoneListtemp).build();
			   

				  given(pateintRepository.findAll()).willReturn(Collections.emptyList());

			        // when -  action or the behaviour that we are going test
			        List<Patient> patientList = patientServiceImpl.getPatients();

			        // then - verify the output
			        assertThat(patientList).isEmpty();
			        assertThat(patientList.size()).isEqualTo(0);

		    }/*
		    
		    // JUnit test for getEmployeeById method
		    @DisplayName("JUnit test for getPatientById method")
		    @Test
		    public void givenPatientId_whenGetPatientById_thenReturnPatientObject(){
		        // given
		        given(pateintRepository.findById(1L)).willReturn(Optional.of(patient));

		        // when
		        Patient savedPatient = patientServiceImpl.getPatientById(patient.getPatient_id());

		        // then
		        assertThat(savedPatient).isNotNull();
		    
		    } 
		    
		    // JUnit test for updateEmployee method
	/*	    @DisplayName("JUnit test for updatePatient method")
		    @Test
		    public void givenPatientObject_whenUpdatePatient_thenReturnUpdatedPatient(){
		        // given - precondition or setup
		        given(pateintRepository.save(patient)).willReturn(patient);
		        patient.setDateOfBirth("01-03-1983");
		      //  patient.setPatient_id(1L);
		        //patient.setPatientName("Rajesh Kumar Mishra");
		        // when -  action or the behaviour that we are going test
		        Patient updatedPatient = patientServiceImpl.updatePatient(patient);

		        // then - verify the output
		        assertThat(updatedPatient.getDateOfBirth()).isEqualTo("01-03-1983");
		        assertThat(updatedPatient.getPatientName()).isEqualTo("ramu");
		   }	*/ 
		    
			// JUnit test for deleteEmployee method
		/*    @DisplayName("JUnit test for deletePatient method")
		    @Test
		    public void givenEmployeeId_whenDeletePatient_thenNothing(){
		        // given - precondition or setup
		        long patient_id = 1L;

		        willDoNothing().given(pateintRepository).deleteById(patient_id);

		        // when -  action or the behaviour that we are going test
		  //      patientServiceImpl.deletePatient(patient_id);

		        // then - verify the output
		        verify(pateintRepository, times(1)).deleteById(patient_id);
		    }
		*/
}
