package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentJpaRepository extends JpaRepository<Appointment, Long> {
}
