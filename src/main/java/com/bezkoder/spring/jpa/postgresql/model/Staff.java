package com.bezkoder.spring.jpa.postgresql.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bezkoder.spring.jpa.postgresql.model.enums.Designation;

@Entity
@Table(name = "staff")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "staff_id")
	private long id;
	
	@Embedded
	private PersonalDetails personalDetails;
	
	@Enumerated(EnumType.STRING)
	private Designation designation;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	public Staff() {
		
	}
	
	public Staff(PersonalDetails personalDetails, Designation designation, Department department) {
		this.personalDetails = personalDetails;
		this.designation = designation;
		this.department = department;
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

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Staff [id=" + id + ", personalDetails=" + personalDetails + ", designation=" + designation
				+ ", department=" + department + "]";
	}
}
