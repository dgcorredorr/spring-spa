package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.WorkHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkHoursRepository extends JpaRepository<WorkHours, Byte> {
}
