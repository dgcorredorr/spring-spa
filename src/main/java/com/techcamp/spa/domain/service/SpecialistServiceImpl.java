package com.techcamp.spa.domain.service;

import com.techcamp.spa.domain.data.SpecialistDto;
import com.techcamp.spa.domain.ports.api.SpecialistServicePort;
import com.techcamp.spa.domain.ports.spi.SpecialistPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class SpecialistServiceImpl implements SpecialistServicePort {

    private final SpecialistPersistencePort specialistPersistencePort;

    public SpecialistServiceImpl(SpecialistPersistencePort specialistPersistencePort) {
        this.specialistPersistencePort = specialistPersistencePort;
    }

    @Override
    public SpecialistDto save(SpecialistDto specialist) {
        return specialistPersistencePort.save(specialist);
    }

    @Override
    public SpecialistDto getById(Short id) {
        return specialistPersistencePort.getById(id);
    }

    @Override
    public Page<SpecialistDto> getAll(Pageable pageable) {
        return specialistPersistencePort.getAll(pageable);
    }

    @Override
    public Page<SpecialistDto> getByNameContainingAndAreaContaining(String name, String area, Pageable pageable) {
        return specialistPersistencePort.getByNameContainingAndAreaContaining(name, area, pageable);
    }

    @Override
    public SpecialistDto update(SpecialistDto specialist) {
        return specialistPersistencePort.update(specialist);
    }

    @Override
    public void deleteById(Short specialistId) {
        specialistPersistencePort.deleteById(specialistId);
    }

    @Override
    public void delete(SpecialistDto specialist) {
        specialistPersistencePort.delete(specialist);
    }
}
