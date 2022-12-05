package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Byte> {
}
