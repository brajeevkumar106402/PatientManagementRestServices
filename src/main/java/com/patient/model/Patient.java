package com.patient.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
/**
 * 
 * @author BK106402 This class is used as entity class for holding details of
 *         Patient
 *
 */
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long patient_id;
	@Column(name = "patient_name")
	private String patientName;
	private String dateOfBirth;
	private String genderCode;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_fk", referencedColumnName = "patient_id",nullable=false)
	private List<Address> addressList = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "patient_fk", referencedColumnName = "patient_id",nullable=false)
	private List<Telephone> telephoneList = new ArrayList<>();

}
