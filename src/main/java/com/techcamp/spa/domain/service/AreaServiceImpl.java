package com.techcamp.spa.domain.service;

import com.techcamp.spa.domain.data.AreaDto;
import com.techcamp.spa.domain.ports.api.AreaServicePort;
import com.techcamp.spa.domain.ports.spi.AreaPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AreaServiceImpl implements AreaServicePort {

    private final AreaPersistencePort areaPersistencePort;

    @Autowired
    public AreaServiceImpl(AreaPersistencePort areaPersistencePort) {
        this.areaPersistencePort = areaPersistencePort;
    }

    @Override
    public AreaDto save(AreaDto area) {
        return areaPersistencePort.save(area);
    }

    @Override
    public List<AreaDto> getAll() {
        return areaPersistencePort.getAll();
    }

    @Override
    public AreaDto update(AreaDto area) {
        return areaPersistencePort.update(area);
    }

    @Override
    public void deleteById(Byte areaId) {
        areaPersistencePort.deleteById(areaId);
    }
}
