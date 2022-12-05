package com.techcamp.spa.domain.service;

import com.techcamp.spa.domain.data.GenderDto;
import com.techcamp.spa.domain.ports.api.GenderServicePort;
import com.techcamp.spa.domain.ports.spi.GenderPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GenderServiceImpl implements GenderServicePort {

    private final GenderPersistencePort genderPersistencePort;

    @Autowired
    public GenderServiceImpl(GenderPersistencePort genderPersistencePort) {
        this.genderPersistencePort = genderPersistencePort;
    }

    @Override
    public GenderDto save(GenderDto gender) {
        return genderPersistencePort.save(gender);
    }

    @Override
    public List<GenderDto> getAll() {
        return genderPersistencePort.getAll();
    }

    @Override
    public GenderDto update(GenderDto gender) {
        return genderPersistencePort.update(gender);
    }

    @Override
    public void deleteById(Byte genderId) {
        genderPersistencePort.deleteById(genderId);
    }
}
