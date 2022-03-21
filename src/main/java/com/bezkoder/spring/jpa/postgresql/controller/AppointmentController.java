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
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jpa.postgresql.model.Appointment;
import com.bezkoder.spring.jpa.postgresql.repository.AppointmentRepository;

@RestController
@RequestMapping("/HMS")
public class AppointmentController {
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@GetMapping("/appointments")
	public ResponseEntity<List<Appointment>> getAllAppointments(){
		try {
			List<Appointment> appointments = appointmentRepository.findAll();
			if(appointments.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(appointments, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/appointments/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable(value = "id") Long id){
		Optional<Appointment> appointment = appointmentRepository.findById(id);
		
		if (appointment.isPresent()) {
			return new ResponseEntity<>(appointment.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/appointments")
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment){
		try {
			Appointment _appointment = appointmentRepository.save(appointment);
			return new ResponseEntity<>(_appointment, HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/appointments/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable(value = "id") long id, @RequestBody Appointment appointment){
		Optional<Appointment> appointments = appointmentRepository.findById(id);
		
		if(appointments.isPresent()) {
			Appointment _appointment = appointments.get();
			_appointment.setDateTime(appointment.getDateTime());
			return new ResponseEntity<>(appointmentRepository.save(_appointment), HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	@DeleteMapping("/appointments")
	public ResponseEntity<Appointment> deleteAppointment(@PathVariable(value = "id") long id){
		try {
			appointmentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
