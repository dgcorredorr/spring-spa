package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialistRepository extends JpaRepository<Specialist, Short> {
}
