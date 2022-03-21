package com.bezkoder.spring.jpa.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bezkoder.spring.jpa.postgresql.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
