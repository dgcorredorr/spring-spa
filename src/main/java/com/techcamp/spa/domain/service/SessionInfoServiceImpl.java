package com.techcamp.spa.domain.service;

import com.techcamp.spa.domain.data.SessionInfoDto;
import com.techcamp.spa.domain.ports.api.SessionInfoServicePort;
import com.techcamp.spa.domain.ports.spi.SessionInfoPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public class SessionInfoServiceImpl implements SessionInfoServicePort {

    private final SessionInfoPersistencePort sessionInfoPersistencePort;

    public SessionInfoServiceImpl(SessionInfoPersistencePort sessionInfoPersistencePort) {
        this.sessionInfoPersistencePort = sessionInfoPersistencePort;
    }

    @Override
    public SessionInfoDto save(SessionInfoDto sessionInfo) {
        return sessionInfoPersistencePort.save(sessionInfo);
    }

    @Override
    public SessionInfoDto getById(Long id) {
        return sessionInfoPersistencePort.getById(id);
    }

    @Override
    public Page<SessionInfoDto> getBySpecialistId(Short id, Pageable pageable) {
        return sessionInfoPersistencePort.getBySpecialistId(id, pageable);
    }

    @Override
    public Page<SessionInfoDto> getByClientId(Long id, Pageable pageable) {
        return sessionInfoPersistencePort.getByClientId(id, pageable);
    }
    @Override
    public Page<SessionInfoDto> getAll(Pageable pageable) {
        return sessionInfoPersistencePort.getAll(pageable);
    }

    @Override
    public Integer isAvailable(Long clientId, Short specialistId, Long appointmentId, LocalDateTime sessionDate) {
        return sessionInfoPersistencePort.isAvailable(clientId, specialistId, appointmentId, sessionDate);
    }

    @Override
    public SessionInfoDto update(SessionInfoDto sessionInfo) {
        return sessionInfoPersistencePort.update(sessionInfo);
    }

    @Override
    public void deleteById(Long sessionInfoId) {
        sessionInfoPersistencePort.deleteById(sessionInfoId);
    }
}
