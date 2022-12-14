package com.techcamp.spa.infrastructure.adapter;

import com.techcamp.spa.domain.data.SessionInfoDto;
import com.techcamp.spa.domain.ports.spi.SessionInfoPersistencePort;
import com.techcamp.spa.infrastructure.mapper.SessionInfoMapper;
import com.techcamp.spa.infrastructure.repository.SessionInfoJdbcRepository;
import com.techcamp.spa.infrastructure.repository.SessionInfoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class SessionInfoPersistenceAdapter implements SessionInfoPersistencePort {

    @Autowired
    private SessionInfoJpaRepository sessionInfoJpaRepository;

    @Autowired
    private SessionInfoJdbcRepository sessionInfoJdbcRepository;

    @Autowired
    private SessionInfoMapper sessionInfoMapper;

    @Override
    public SessionInfoDto save(SessionInfoDto sessionInfoDto) throws DataIntegrityViolationException {
        if (isAvailable(sessionInfoDto.getClientId(), sessionInfoDto.getSpecialistId(), sessionInfoDto.getAppointmentId(), sessionInfoDto.getSessionDate()) == 1) {
            return sessionInfoMapper.toDomain(sessionInfoJpaRepository.save(sessionInfoMapper.toEntity(sessionInfoDto)));
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public SessionInfoDto getById(Long id) throws NoSuchElementException {
        return sessionInfoMapper.toDomain(sessionInfoJpaRepository.findById(id).orElseThrow());
    }

    @Override
    public Page<SessionInfoDto> getAll(Pageable pageable) {
        Page<SessionInfoDto> sessionInfoPage = sessionInfoMapper.toDomainPage(sessionInfoJpaRepository.findAll(pageable));
        if (sessionInfoPage.isEmpty()) {
            throw new NoSuchElementException();
        }
        return sessionInfoPage;
    }

    @Override
    public Integer isAvailable(Long clientId, Short specialistId, Long appointmentId, LocalDateTime sessionDate) {
        return sessionInfoJdbcRepository.isAvailable(clientId, specialistId, appointmentId, sessionDate);
    }

    @Override
    public SessionInfoDto update(SessionInfoDto sessionInfoDto) throws NoSuchElementException {
        if (sessionInfoDto.getSessionInfoId() == null) {
            throw new DataIntegrityViolationException("SessionInfoId no ingresado");
        } else if (sessionInfoJpaRepository.existsById(sessionInfoDto.getSessionInfoId())) {
            return save(sessionInfoDto);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(Long sessionInfoId) throws NoSuchElementException {
        if (sessionInfoJpaRepository.existsById(sessionInfoId)) {
            sessionInfoJpaRepository.deleteById(sessionInfoId);
        } else {
            throw new NoSuchElementException();
        }
    }
}
