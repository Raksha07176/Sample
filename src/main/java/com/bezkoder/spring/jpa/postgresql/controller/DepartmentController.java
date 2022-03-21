package com.bezkoder.spring.jpa.postgresql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jpa.postgresql.model.Department;
import com.bezkoder.spring.jpa.postgresql.repository.DepartmentRepository;

@RestController
@RequestMapping("/HMS")
public class DepartmentController {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
   @GetMapping("/departments")
	public ResponseEntity<List<Department>> getAllDepartments() {
		try {
			List<Department> departments = departmentRepository.findAll();

			if (departments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(departments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/departments/{id}")
	  public ResponseEntity<Department> getDepartmentById(@PathVariable(value = "id") Long id) {
		Optional<Department> department = departmentRepository.findById(id);

		if (department.isPresent()) {
			return new ResponseEntity<>(department.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	  }
	
	@GetMapping("/departments/name")
	public ResponseEntity<List<Department>> getDepartmentByName(@RequestParam String departmentName){
		try {
			List<Department> departments = departmentRepository.findByDepartmentName(departmentName);

			if (departments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(departments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/departments")
	 public ResponseEntity<Department> createDepartment(@RequestBody Department department){
		try {
			Department _department = departmentRepository
					.save(department);
			return new ResponseEntity<>(_department, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
}
	
	/*@PutMapping("/departments/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable("id") long id, @RequestBody Department department) {
		Optional<Department> departmentData = Optional.of(departmentRepository.findById(id));

		if (departmentData.isPresent()) {
			Department _department = departmentData.get();
			_department.setDepartmentName(department.getDepartmentName());
			return new ResponseEntity<>(departmentRepository.save(_department), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}*/
	
	@DeleteMapping("/departments/{id}")
	public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") long id) {
		try {
			departmentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
