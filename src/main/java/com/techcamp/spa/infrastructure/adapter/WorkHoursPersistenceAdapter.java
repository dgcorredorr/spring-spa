package com.techcamp.spa.infrastructure.adapter;

import com.techcamp.spa.domain.data.WorkHoursDto;
import com.techcamp.spa.domain.ports.spi.WorkHoursPersistencePort;
import com.techcamp.spa.infrastructure.mapper.WorkHoursMapper;
import com.techcamp.spa.infrastructure.repository.WorkHoursJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WorkHoursPersistenceAdapter implements WorkHoursPersistencePort {

    @Autowired
    private WorkHoursJpaRepository workHoursJpaRepository;

    @Autowired
    private WorkHoursMapper workHoursMapper;

    @Override
    public WorkHoursDto save(WorkHoursDto workHoursDto) throws DataIntegrityViolationException {
        return workHoursMapper.toDomain(workHoursJpaRepository.save(workHoursMapper.toEntity(workHoursDto)));
    }

    @Override
    public List<WorkHoursDto> getAll() {
        List<WorkHoursDto> workHoursList = workHoursMapper.toDomainList(workHoursJpaRepository.findAll());
        if (workHoursList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return workHoursList;
    }

    @Override
    public WorkHoursDto update(WorkHoursDto workHoursDto) throws NoSuchElementException {
        if (workHoursDto.getWorkHoursId() == null) {
            throw new DataIntegrityViolationException("WorkHoursId no ingresado");
        } else if (workHoursJpaRepository.existsById(workHoursDto.getWorkHoursId())) {
            return save(workHoursDto);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(Byte workHoursId) throws NoSuchElementException {
        if (workHoursJpaRepository.existsById(workHoursId)) {
            workHoursJpaRepository.deleteById(workHoursId);
        } else {
            throw new NoSuchElementException();
        }
    }
}
