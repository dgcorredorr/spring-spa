package com.techcamp.spa.infrastructure.adapter;

import com.techcamp.spa.domain.data.SpecialistDto;
import com.techcamp.spa.domain.ports.spi.SpecialistPersistencePort;
import com.techcamp.spa.infrastructure.mapper.SpecialistMapper;
import com.techcamp.spa.infrastructure.repository.SpecialistJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SpecialistPersistenceAdapter implements SpecialistPersistencePort {
    @Autowired
    private SpecialistJpaRepository specialistJpaRepository;
    @Autowired
    private SpecialistMapper specialistMapper;

    @Override
    public SpecialistDto save(SpecialistDto specialist) throws DataIntegrityViolationException {
        return specialistMapper.toDomain(specialistJpaRepository.save(specialistMapper.toEntity(specialist)));
    }

    @Override
    public SpecialistDto getById(Short id) throws NoSuchElementException {
        return specialistMapper.toDomain(specialistJpaRepository.findById(id).orElseThrow());
    }

    @Override
    public Page<SpecialistDto> getAll(Pageable pageable) throws NoSuchElementException {
        Page<SpecialistDto> specialistPage = specialistMapper.toDomainPage(specialistJpaRepository.findAll(pageable));
        if (specialistPage.isEmpty()) {
            throw new NoSuchElementException();
        }
        return specialistPage;
    }

    @Override
    public Page<SpecialistDto> getByNameContainingAndAreaContaining(String name, String area, Pageable pageable) throws NoSuchElementException {
        Page<SpecialistDto> specialistPage = specialistMapper.toDomainPage(specialistJpaRepository.getByAreaContainingIgnoreCaseOrNameContainingIgnoreCase(name, area, pageable));
        if (specialistPage.isEmpty()) {
            throw new NoSuchElementException();
        }
        return specialistPage;
    }

    @Override
    public SpecialistDto update(SpecialistDto specialistDto) throws NoSuchElementException {
        if (specialistDto.getSpecialistId() == null) {
            throw new DataIntegrityViolationException("SpecialistId no ingresado");
        } else if (specialistJpaRepository.existsById(specialistDto.getSpecialistId())) {
            return save(specialistDto);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(Short specialistId) throws NoSuchElementException {
        if (specialistJpaRepository.existsById(specialistId)) {
            specialistJpaRepository.deleteById(specialistId);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void delete(SpecialistDto specialistDto) throws NoSuchElementException {
        if (specialistDto.getSpecialistId() == null) {
            throw new DataIntegrityViolationException("SpecialistId no ingresado");
        } else if (specialistJpaRepository.existsById(specialistDto.getSpecialistId())) {
            specialistJpaRepository.delete(specialistMapper.toEntity(specialistDto));
        } else {
            throw new NoSuchElementException();
        }
    }
}
