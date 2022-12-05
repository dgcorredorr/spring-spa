package com.techcamp.spa.domain.ports.spi;

import com.techcamp.spa.domain.data.GenderDto;

import java.util.List;

public interface GenderPersistencePort {

    GenderDto save(GenderDto gender);

    List<GenderDto> getAll();

    GenderDto update(GenderDto gender);

    void deleteById(Byte genderId);

}
