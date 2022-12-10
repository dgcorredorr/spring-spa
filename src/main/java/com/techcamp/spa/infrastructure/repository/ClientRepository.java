package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "SELECT " +
            "c.client_id," +
            "c.gender_id," +
            "c.document_type_id," +
            "c.membership_id," +
            "c.first_name," +
            "c.middle_name," +
            "c.last_name," +
            "c.document_number," +
            "c.birth_date " +
            "FROM Client c " +
            "WHERE (lower(c.first_name) LIKE lower(concat('%',concat(?1,'%'))) OR " +
            "lower(c.last_name) LIKE lower(concat('%',concat(?1,'%'))) OR " +
            "lower(c.middle_name) LIKE lower(concat('%',concat(?1,'%')))) " +
            "AND lower(c.document_number) LIKE lower(concat('%',concat(?2,'%')))",
            countQuery = "SELECT count(1) FROM Client",
            nativeQuery = true)
    Page<Client> getByDocumentNumberContainingIgnoreCaseOrNameContainingIgnoreCase(String name, String documentNumber, Pageable pageable);
}
