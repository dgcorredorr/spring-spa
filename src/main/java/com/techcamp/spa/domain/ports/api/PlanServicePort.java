package com.techcamp.spa.domain.ports.api;

import com.techcamp.spa.domain.data.PlanDto;

import java.util.List;

public interface PlanServicePort {
    PlanDto save(PlanDto plan);

    List<PlanDto> getAll();

    PlanDto update(PlanDto plan);

    void deleteById(Byte planId);
}
