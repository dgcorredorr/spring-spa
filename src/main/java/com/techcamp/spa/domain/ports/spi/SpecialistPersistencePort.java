package com.techcamp.spa.domain.ports.spi;

import com.techcamp.spa.domain.data.SpecialistDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SpecialistPersistencePort {

    SpecialistDto save(SpecialistDto specialist);

    SpecialistDto getById(Short id);

    Page<SpecialistDto> getAll(Pageable pageable);

    Page<SpecialistDto> getByNameContainingAndAreaContaining(String name, String area, Pageable pageable);

    SpecialistDto update(SpecialistDto specialist);

    void deleteById(Short specialistId);

    void delete(SpecialistDto specialist);

}
