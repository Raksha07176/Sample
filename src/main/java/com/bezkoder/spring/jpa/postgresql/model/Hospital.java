package com.bezkoder.spring.jpa.postgresql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hospitals")
public class Hospital {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "hospital_id")
	private long id;

	@Column(name = "hospitalname")
	private String hospitalName;

	@Column(name = "address")
	private String address;

	@Column(name = "contactno")
	private long contactNo;

	public Hospital() {
		
	}
	
	public Hospital(String hospitalName, String address, long contactNo) {
		this.hospitalName = hospitalName;
		this.address = address;
		this.contactNo = contactNo;
	}

	public long getId() {
		return id;
	}
	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}
	@Override
	public String toString() {
		return "Hospital [id=" + id + ", title=" + hospitalName + ", address=" + address + ", contactNo=" + contactNo + "]";
	}

}
