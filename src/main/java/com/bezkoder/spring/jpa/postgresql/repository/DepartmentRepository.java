package com.bezkoder.spring.jpa.postgresql.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.postgresql.model.Department;

public interface DepartmentRepository extends JpaRepository <Department, Long> {
	List<Department> findByDepartmentName(String departmentName);
}
