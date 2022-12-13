package com.techcamp.spa.infrastructure.repository;

import com.techcamp.spa.infrastructure.entity.SessionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionInfoJpaRepository extends JpaRepository<SessionInfo, Long> {
}
