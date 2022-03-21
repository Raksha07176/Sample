package com.bezkoder.spring.jpa.postgresql.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "patient_id")
	private long id;
	
	@Embedded
	private PersonalDetails personalDetails;
	
	@Embedded
	private Address address;
	
	public Patient() {
		
	}

	public Patient(PersonalDetails personalDetails, Address address) {
		this.personalDetails = personalDetails;
		this.address = address;
	}

	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", person=" + personalDetails + ", address=" + address + "]";
	}
}
