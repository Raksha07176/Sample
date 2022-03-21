package com.bezkoder.spring.jpa.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.postgresql.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	List<Patient> findByPersonalDetailsFirstName(String firstName);
	List<Patient> findByPersonalDetailsContactNo(long contactNo);
}
