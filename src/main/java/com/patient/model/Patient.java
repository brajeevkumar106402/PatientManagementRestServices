package com.patient.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long patientId;
	String patientName;
	String dateOfBirth;
	String genderCode;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pc_fid", referencedColumnName = "patientId")
	private List<Address> addressList = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "pc_fid", referencedColumnName = "patientId")
	private List<Telephone> telephoneList = new ArrayList<>();

}
