package com.bezkoder.spring.jpa.postgresql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jpa.postgresql.model.Staff;
import com.bezkoder.spring.jpa.postgresql.repository.StaffRepository;

@RestController
@RequestMapping("/HMS")
public class StaffController {
	
	@Autowired
	private StaffRepository staffRepository;
	
	@GetMapping("/staff")
	public ResponseEntity<List<Staff>> getAllStaff(){
		try {
			List<Staff> staff = staffRepository.findAll();
			
			if(staff.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(staff, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/staff/{id}")
	public ResponseEntity<Staff> getStaffById(@PathVariable(value = "id") Long id){
		Optional<Staff> staff = staffRepository.findById(id);
		
		if(staff.isPresent()) {
			return new ResponseEntity<>(staff.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("staff/doctor/name")
	public ResponseEntity<List<Staff>> getStaffByName(@RequestParam String firstName){
		try {
			List<Staff> staff = staffRepository.findByPersonalDetailsFirstName(firstName);

			if (staff.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(staff, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/staff")
	public ResponseEntity<Staff> createStaff(@RequestBody Staff staff){
		try{
			Staff _staff = staffRepository.save(staff);
			return new ResponseEntity<>(_staff, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
