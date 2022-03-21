package com.bezkoder.spring.jpa.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.postgresql.model.Hospital;

public interface HospitalRepository extends JpaRepository <Hospital, Long> {
	List<Hospital> findByHospitalNameContaining(String hospitalName);
}
