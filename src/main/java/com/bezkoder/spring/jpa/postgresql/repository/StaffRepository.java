package com.bezkoder.spring.jpa.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.postgresql.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	List<Staff>findByPersonalDetailsFirstName(String firstName);
}
