package com.patient.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.patient.model.Address;
import com.patient.model.Patient;
import com.patient.model.Telephone;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientRepositoryTest {

	@Autowired
	PatientRepository pateintRepository;

	@Test
	@Order(1)
	@Rollback(value = false)
	public void CreatePatientTest() {
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

		Patient pateint1 = Patient.builder().patient_id(1L).patientName("ramu").genderCode("MALE")
				.dateOfBirth("01-03-1984").addressList(addressListtemp).telephoneList(telephoneListtemp).build();
		pateintRepository.save(pateint1);
		Assertions.assertThat(pateint1.getPatient_id()).isGreaterThan(0L);

	}

	@Test
	@Order(2)
	@Rollback(value = false)
	public void getPatientsTest() {
		Patient pateint1 = pateintRepository.findById(1L).get();
		Assertions.assertThat(pateint1.getPatient_id()).isEqualTo(1L);

	}

	@Test
	@Order(3)
	@Rollback(value = false)
	public void getListOfPatientsTest() {
		List<Patient> pateints = pateintRepository.findAll();
		Assertions.assertThat(pateints.size()).isGreaterThan(0);

	}

	@Test
	@Order(4)
	@Rollback(value = false)
	public void updatePatientsTest() {
		Patient pateint1 = pateintRepository.findById(1L).get();
		pateint1.setPatientName("rakesh singh");
		pateint1.setDateOfBirth("04-03-1985");
		Patient pateintUpdated = pateintRepository.save(pateint1);
		Assertions.assertThat(pateintUpdated.getPatientName()).isEqualTo("rakesh singh");

	}

	@Test
	@Order(5)
	@Rollback(value = false)
	void isPateintExistsByIdtest() {
		Boolean actualResult = pateintRepository.isPateintExistsById(1L);
		assertThat(actualResult).isTrue();
	}

	@Test
	@Order(6)
	@Rollback(value = false)
	void isPateintExistsByNametest() {
		Boolean actualResult = pateintRepository.isPateintExistsByName("rakesh singh");
		assertThat(actualResult).isTrue();
	}

	@Test
	@Order(7)
	@Rollback(value = false)
	public void deletePatientTest() {
		Patient pateint = pateintRepository.findById(1L).get();
		pateintRepository.delete(pateint);
		Patient pateintRecovered = null;
		Optional<Patient> optionalPatient = pateintRepository.findByPatientName("rakesh singh");
		if (optionalPatient.isPresent()) {
			pateintRecovered = optionalPatient.get();
		}
		Assertions.assertThat(pateintRecovered).isNull();

	}

}
