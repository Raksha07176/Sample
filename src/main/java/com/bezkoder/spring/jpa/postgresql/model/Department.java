package com.bezkoder.spring.jpa.postgresql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "department_id")
	private long id;
	
	@Column(name = "departmentname")
	private String departmentName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;
	
     public Department() {
		
	}

	public Department(String departmentName, Hospital hospital) {
		this.departmentName = departmentName;
		this.hospital = hospital;
		
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Hospital getHospital() {
		return hospital;
	}
	
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + ", hospital=" + hospital + "]";
	}
	
}
