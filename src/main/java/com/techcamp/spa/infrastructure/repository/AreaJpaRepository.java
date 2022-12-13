package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaJpaRepository extends JpaRepository<Area, Byte> {
}
