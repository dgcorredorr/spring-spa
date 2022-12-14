package com.techcamp.spa.domain.ports.spi;

import com.techcamp.spa.domain.data.PlanDto;

import java.util.List;

public interface PlanPersistencePort {
    PlanDto save(PlanDto plan);

    List<PlanDto> getAll();

    PlanDto getById(Byte id);

    PlanDto update(PlanDto plan);

    void deleteById(Byte planId);
}
