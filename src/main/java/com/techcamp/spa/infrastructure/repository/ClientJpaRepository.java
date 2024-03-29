package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientJpaRepository extends JpaRepository<Client, Long> {
    @Query(value = "SELECT " +
            "c " +
            "FROM Client c " +
            "WHERE (lower(c.firstName) LIKE lower(concat('%',concat(?1,'%'))) OR " +
            "lower(c.lastName) LIKE lower(concat('%',concat(?1,'%'))) OR " +
            "lower(c.middleName) LIKE lower(concat('%',concat(?1,'%')))) " +
            "OR lower(c.documentNumber) LIKE lower(concat('%',concat(?2,'%')))")
    Page<Client> getByDocumentNumberContainingIgnoreCaseOrNameContainingIgnoreCase(String name, String documentNumber, Pageable pageable);
}
