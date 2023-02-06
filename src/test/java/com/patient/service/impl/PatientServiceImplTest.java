package com.patient.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.patient.exception.PatientIdNotFoundException;
import com.patient.model.Address;
import com.patient.model.Patient;
import com.patient.model.Telephone;
import com.patient.repo.PatientRepository;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

	@Mock
	PatientRepository pateintRepository;

	@InjectMocks
	private PatientServiceImpl patientServiceImpl;

	private static Patient patient;

	@BeforeEach
	public void setup() {
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

		patient = Patient.builder().patient_id(1L).patientName("brajeev").genderCode("MALE").dateOfBirth("01-03-1984")
				.addressList(addressListtemp).telephoneList(telephoneListtemp).build();
	}

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
	void createPatientTest() {
		when(pateintRepository.save(Mockito.any(Patient.class))).thenReturn(patient);
		Patient actual = patientServiceImpl.createPatient(patient);
		assertEquals(patient.getPatientName(), actual.getPatientName());
	}

	@Test
	void getPatientListTest() {
		List<Patient> patients = Arrays.asList(patient);
		when(pateintRepository.findAll()).thenReturn(patients);
		List<Patient> actual = patientServiceImpl.getPatients();
		assertEquals(patients.size(), actual.size());
		assertDoesNotThrow(() -> patientServiceImpl.getPatients());
		verify(pateintRepository, times(2)).findAll();
	}

	@Test
	void testGetPatientListForPatientDetailsNotFoundException() {
		when(pateintRepository.findAll()).thenReturn(new ArrayList<>());
		assertThrows(PatientIdNotFoundException.class, () -> patientServiceImpl.getPatients());
		String expected = "patient List is empty";
		try {
			patientServiceImpl.getPatients();
			fail("Test expecting PatientDetailsNotFoundException");
		} catch (PatientIdNotFoundException pdnfe) {
			assertEquals(expected, pdnfe.getMessage());
		}
	}

	@Test
	public void getPatientByIdTest() {
		when(pateintRepository.findById(1L)).thenReturn(createEmployeeStub());
		Patient testedPatient = patientServiceImpl.getPatientById(1L);
		Assertions.assertThat(testedPatient.getPatientName()).isEqualTo("cernerPatient");

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
	}

	@DisplayName("JUnit test for savePatient method")
	@Test
	public void givenPatientObject_whenSavePatient_thenReturnPatientObject() {
		// given - precondition or setup
		// given(pateintRepository.findByPatientName(pateint.getPatientName()))
		// .willReturn(Optional.empty());

		given(pateintRepository.save(patient)).willReturn(patient);

		System.out.println(pateintRepository);
		System.out.println(patientServiceImpl);

		// when - action or the behaviour that we are going test
		Patient savedPatient = patientServiceImpl.createPatient(patient);

		System.out.println(savedPatient);
		// then - verify the output
		assertThat(savedPatient).isNotNull();
	}

	// JUnit test for getAllEmployees method
	@DisplayName("JUnit test for getPatients method")
	@Test
	public void givenPatientsList_whenGetAllPatients_thenReturnPatientsList() {
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

		Patient patient1 = Patient.builder().patient_id(1L).patientName("ramu").genderCode("MALE")
				.dateOfBirth("01-03-1984").addressList(addressListtemp).telephoneList(telephoneListtemp).build();

		given(pateintRepository.findAll()).willReturn(List.of(patient, patient1));

		// when - action or the behaviour that we are going test
		List<Patient> patientList = patientServiceImpl.getPatients();

		// then - verify the output
		assertThat(patientList).isNotNull();
		assertThat(patientList.size()).isEqualTo(2);
	}

	@Test
	void deletePatientByIdTest() {
		when(pateintRepository.existsById(1L)).thenReturn(true);
		String statusMessage = patientServiceImpl.deletePatient(1L);
		assertEquals("Patient successfully deleted with patient id = 1", statusMessage);
		verify(pateintRepository, times(1)).existsById(anyLong());
		verify(pateintRepository, times(1)).deleteById(anyLong());
	}

	@Test
	void deletePatientByIdForPatientDetailsNotFoundExceptionTest() {
		when(pateintRepository.existsById(1L)).thenReturn(false);
		assertThrows(PatientIdNotFoundException.class, () -> patientServiceImpl.deletePatient(1L));
		String expected = "Patient Id not found in db with given patient id = 1";
		try {
			patientServiceImpl.deletePatient(1L);
			fail("Test expecting PatientDetailsNotFoundException");
		} catch (PatientIdNotFoundException pdnfe) {
			assertEquals(expected, pdnfe.getMessage());
		}

	}

	@Test
	void updatePatientByIdTest() {
		Optional<Patient> optionalPatient = Optional.ofNullable(patient);
		when(pateintRepository.findById(1L)).thenReturn(optionalPatient);
		// when(pateintRepository.save(any(Patient.class))).thenReturn(patient);
		when(pateintRepository.save(Mockito.any(Patient.class))).thenReturn(patient);
		// Mockito.any(Class<T> type) and Mockito.anyMapOf(Class<K> keyClazz, Class<V>
		// valueClazz)

		Patient actual = patientServiceImpl.updatePatient(patient, 1L);
		assertEquals(patient.getPatientName(), actual.getPatientName());
		assertEquals(new Long(1), actual.getPatient_id());
		verify(pateintRepository, times(1)).findById(anyLong());
		verify(pateintRepository, times(1)).save(Mockito.any(Patient.class));
	}

	@Test
	void testUpdatePatientByIdForPatientIdNotFoundException() {
		when(pateintRepository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(NoSuchElementException.class, () -> patientServiceImpl.updatePatient(patient, 1L));

		String expected = "No value present";
		try {
			patientServiceImpl.updatePatient(patient, 1L);
			fail("Test expecting NoSuchElementException");
		} catch (NoSuchElementException ex) {
			assertEquals(expected, ex.getMessage());
		}
	}

	@Test
	void getPatientDetailByIdTest() {
		Optional<Patient> optionalPatient = Optional.ofNullable(patient);
		when(pateintRepository.findById(1L)).thenReturn(optionalPatient);
		Patient actual = patientServiceImpl.getPatientById(1L);

		assertEquals(patient.getPatientName(), actual.getPatientName());

		verify(pateintRepository, times(1)).findById(1L);
	}

	@Test
	void getPatientDetailByIdForNoSuchElementExceptionTest() {
		when(pateintRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(NoSuchElementException.class, () -> patientServiceImpl.getPatientById(1L));
		String expected = "No value present";
		try {
			patientServiceImpl.getPatientById(1L);
			fail("Test expecting NoSuchElementException");
		} catch (NoSuchElementException nseex) {
			assertEquals(expected, nseex.getMessage());
		}
		verify(pateintRepository, times(2)).findById(1L);
	}

	@Test
	void getPatientByPatientNameTest() {
		Optional<Patient> optionalPatient = Optional.ofNullable(patient);
		when(pateintRepository.findByPatientName("brajeev")).thenReturn(optionalPatient);
		Patient actual = patientServiceImpl.getPatientByName("brajeev");

		assertEquals(patient.getPatientName(), actual.getPatientName());

		verify(pateintRepository, times(1)).findByPatientName("brajeev");
	}

	@Test
	void getPatientByNameForNoSuchElementExceptionTest() {
		when(pateintRepository.findByPatientName("brajeev")).thenReturn(Optional.empty());
		assertThrows(NoSuchElementException.class, () -> patientServiceImpl.getPatientById(1L));
		String expected = "No value present";
		try {
			patientServiceImpl.getPatientByName("brajeev");
			fail("Test expecting NoSuchElementException");
		} catch (NoSuchElementException nseex) {
			assertEquals(expected, nseex.getMessage());
		}
		verify(pateintRepository, times(1)).findByPatientName("brajeev");
	}

	@Test
	void isPateintExistsByIdTest() {
		when(pateintRepository.isPateintExistsById(1L)).thenReturn(Boolean.TRUE);
		Boolean actual = patientServiceImpl.isPateintExistsById(1L);
		assertEquals(Boolean.TRUE, actual.booleanValue());
		verify(pateintRepository, times(1)).isPateintExistsById(1L);
	}

	@Test
	void isPateintExistsByNameTest() {
		when(pateintRepository.isPateintExistsByName("brajeev")).thenReturn(Boolean.TRUE);
		Boolean actual = patientServiceImpl.isPateintExistsByName("brajeev");
		assertEquals(Boolean.TRUE, actual.booleanValue());
		verify(pateintRepository, times(1)).isPateintExistsByName("brajeev");
	}

}
