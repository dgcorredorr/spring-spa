package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipJpaRepository extends JpaRepository<Membership,Byte> {
}
