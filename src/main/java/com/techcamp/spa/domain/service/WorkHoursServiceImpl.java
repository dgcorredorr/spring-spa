package com.techcamp.spa.domain.service;

import com.techcamp.spa.domain.data.WorkHoursDto;
import com.techcamp.spa.domain.ports.api.WorkHoursServicePort;
import com.techcamp.spa.domain.ports.spi.WorkHoursPersistencePort;

import java.util.List;

public class WorkHoursServiceImpl implements WorkHoursServicePort {

    private final WorkHoursPersistencePort workHoursPersistencePort;

    public WorkHoursServiceImpl(WorkHoursPersistencePort workHoursPersistencePort) {
        this.workHoursPersistencePort = workHoursPersistencePort;
    }

    @Override
    public WorkHoursDto save(WorkHoursDto workHours) {
        return workHoursPersistencePort.save(workHours);
    }

    @Override
    public List<WorkHoursDto> getAll() {
        return workHoursPersistencePort.getAll();
    }

    @Override
    public WorkHoursDto update(WorkHoursDto workHours) {
        return workHoursPersistencePort.update(workHours);
    }

    @Override
    public void deleteById(Byte workHoursId) {
        workHoursPersistencePort.deleteById(workHoursId);
    }
}
