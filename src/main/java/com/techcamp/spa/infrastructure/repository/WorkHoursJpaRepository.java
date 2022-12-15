package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.WorkHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkHoursJpaRepository extends JpaRepository<WorkHours, Byte> {
}
