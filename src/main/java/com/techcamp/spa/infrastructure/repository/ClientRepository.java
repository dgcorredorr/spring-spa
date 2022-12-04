package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Page<Client> findByDocumentNumberContainingIgnoreCase(String documentNumber, Pageable pageable);

    Page<Client> findByFirstNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrLastNameContainsIgnoreCase(String firstName, String middleName, String lastName, Pageable pageable);

}
