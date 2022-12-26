package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.Specialist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialistJpaRepository extends JpaRepository<Specialist, Short> {
    @Query("SELECT s FROM Specialist s " +
            "INNER JOIN s.area a " +
            "ON (lower(s.firstName) LIKE lower(concat('%', ?1,'%')) OR lower(s.lastName) LIKE lower(concat('%', ?1,'%')) OR lower(s.middleName) LIKE lower(concat('%', ?1,'%'))) OR lower(a.description) LIKE lower(concat('%', ?2,'%')) ")
    Page<Specialist> getByAreaContainingIgnoreCaseOrNameContainingIgnoreCase(String name, String area, Pageable pageable);
}
