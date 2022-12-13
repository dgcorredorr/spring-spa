package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderJpaRepository extends JpaRepository<Gender,Byte> {
}
