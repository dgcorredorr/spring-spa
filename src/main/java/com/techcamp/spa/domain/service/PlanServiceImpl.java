package com.techcamp.spa.domain.service;

import com.techcamp.spa.domain.data.PlanDto;
import com.techcamp.spa.domain.ports.api.PlanServicePort;
import com.techcamp.spa.domain.ports.spi.PlanPersistencePort;

import java.util.List;

public class PlanServiceImpl implements PlanServicePort {
    private final PlanPersistencePort planPersistencePort;

    public PlanServiceImpl(PlanPersistencePort planPersistencePort) {
        this.planPersistencePort = planPersistencePort;
    }

    @Override
    public PlanDto save(PlanDto plan) {
        return planPersistencePort.save(plan);
    }

    @Override
    public List<PlanDto> getAll() {
        return planPersistencePort.getAll();
    }

    @Override
    public PlanDto getById(Byte id) {
        return planPersistencePort.getById(id);
    }

    @Override
    public PlanDto update(PlanDto plan) {
        return planPersistencePort.update(plan);
    }

    @Override
    public void deleteById(Byte planId) {
        planPersistencePort.deleteById(planId);
    }
}
