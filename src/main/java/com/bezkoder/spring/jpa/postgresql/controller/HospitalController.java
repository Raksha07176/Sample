package com.bezkoder.spring.jpa.postgresql.controller;

import java.util.ArrayList;
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

import com.bezkoder.spring.jpa.postgresql.model.Hospital;
import com.bezkoder.spring.jpa.postgresql.repository.HospitalRepository;

@RestController
@RequestMapping("/HMS")
public class HospitalController {
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@GetMapping("/hospitals")
	public ResponseEntity<List<Hospital>> getAllHospitals(@RequestParam(required = false) String hospitalName) {
		try {
			List<Hospital> hospitals = new ArrayList<Hospital>();

			if (hospitalName == null)
				hospitalRepository.findAll().forEach(hospitals::add);
			else
				hospitalRepository.findByHospitalNameContaining(hospitalName).forEach(hospitals::add);

			if (hospitals.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(hospitals, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/hospitals/{id}")
	public ResponseEntity<Hospital> getHospitalById(@PathVariable("id") long id) {
		Optional<Hospital> HospitalData = hospitalRepository.findById(id);

		if (HospitalData.isPresent()) {
			return new ResponseEntity<>(HospitalData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/hospitals")
	public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
		try {
			Hospital _hospital = hospitalRepository
					.save(new Hospital(hospital.getHospitalName(), hospital.getAddress(), hospital.getContactNo()));
			return new ResponseEntity<>(_hospital, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	}

