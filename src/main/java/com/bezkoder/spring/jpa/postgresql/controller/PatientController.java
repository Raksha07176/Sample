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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jpa.postgresql.model.Patient;
import com.bezkoder.spring.jpa.postgresql.model.PersonalDetails;
import com.bezkoder.spring.jpa.postgresql.repository.PatientRepository;

@RestController
@RequestMapping("/HMS")
public class PatientController {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping("/patients")
	public ResponseEntity<List<Patient>>getAllPatients(){
		try {
			List<Patient> patients = patientRepository.findAll();
			
			if(patients.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(patients, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/patient/{id}")
	public ResponseEntity<Patient>getPatientById(@PathVariable(value = "id") Long id){
		Optional<Patient>patient = patientRepository.findById(id);
		
		if(patient.isPresent()) {
			return new ResponseEntity<>(patient.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("patient/name")
	public ResponseEntity<List<Patient>> getPatientByName(@RequestParam String firstName){
		try {
			List<Patient> patients = patientRepository.findByPersonalDetailsFirstName(firstName);

			if (patients.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(patients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@PostMapping("/patients")
	public ResponseEntity<Patient>createPatient(@RequestBody Patient patient){
		try {
			Patient _patient = patientRepository.save(patient);
			return new ResponseEntity<>(_patient, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<Patient>deletePatient(@PathVariable(value = "id") long id){
		try {
			patientRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
