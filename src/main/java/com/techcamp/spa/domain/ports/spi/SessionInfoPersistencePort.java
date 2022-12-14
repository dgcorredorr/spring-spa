package com.techcamp.spa.domain.ports.spi;

import com.techcamp.spa.domain.data.SessionInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface SessionInfoPersistencePort {
    SessionInfoDto save(SessionInfoDto sessionInfo);

    SessionInfoDto getById(Long id);

    Page<SessionInfoDto> getAll(Pageable pageable);

    Integer isAvailable(Long clientId, Short specialistId, Long appointmentId, LocalDateTime sessionDate);

    SessionInfoDto update(SessionInfoDto sessionInfo);

    void deleteById(Long sessionInfoId);
}
