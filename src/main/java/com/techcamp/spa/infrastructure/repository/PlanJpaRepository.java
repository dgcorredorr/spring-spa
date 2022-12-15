package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanJpaRepository extends JpaRepository<Plan, Byte> {
}
