package com.techcamp.spa.infrastructure.adapter;

import com.techcamp.spa.domain.data.PlanDto;
import com.techcamp.spa.domain.ports.spi.PlanPersistencePort;
import com.techcamp.spa.infrastructure.mapper.PlanMapper;
import com.techcamp.spa.infrastructure.repository.PlanJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PlanPersistenceAdapter implements PlanPersistencePort {

    @Autowired
    private PlanJpaRepository planJpaRepository;

    @Autowired
    private PlanMapper planMapper;

    @Override
    public PlanDto save(PlanDto planDto) throws DataIntegrityViolationException {
        return planMapper.toDomain(planJpaRepository.save(planMapper.toEntity(planDto)));
    }

    @Override
    public List<PlanDto> getAll() {
        List<PlanDto> planList = planMapper.toDomainList(planJpaRepository.findAll());
        if (planList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return planList;
    }

    @Override
    public PlanDto getById(Byte id) throws NoSuchElementException {
        return planMapper.toDomain(planJpaRepository.findById(id).orElseThrow());
    }

    @Override
    public PlanDto update(PlanDto planDto) throws NoSuchElementException {
        if (planDto.getPlanId() == null) {
            throw new DataIntegrityViolationException("PlanId no ingresado");
        } else if (planJpaRepository.existsById(planDto.getPlanId())) {
            return save(planDto);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(Byte planId) throws NoSuchElementException {
        if (planJpaRepository.existsById(planId)) {
            planJpaRepository.deleteById(planId);
        } else {
            throw new NoSuchElementException();
        }
    }
}
