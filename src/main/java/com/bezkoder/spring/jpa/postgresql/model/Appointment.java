package com.bezkoder.spring.jpa.postgresql.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "appointments")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "appointment_id")
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_tm")
	private Date dateTime;
	
	@Column(name = "symptoms")
	private String symptoms;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Staff staff;
	
	public Appointment() {
		
	}

	public Appointment(Date dateTime, String symptoms, Patient patient, Staff staff) {
		this.dateTime = dateTime;
		this.symptoms = symptoms;
		this.patient = patient;
		this.staff = staff;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", dateTime=" + dateTime + ", symptoms=" + symptoms + ", patient=" + patient
				+ ", staff=" + staff + "]";
	}
}
