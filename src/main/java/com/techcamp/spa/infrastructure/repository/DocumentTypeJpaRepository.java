package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeJpaRepository extends JpaRepository<DocumentType, Byte> {
}
