package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Byte> {
}
