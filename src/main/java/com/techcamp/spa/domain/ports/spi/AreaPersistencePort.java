package com.techcamp.spa.domain.ports.spi;

import com.techcamp.spa.domain.data.AreaDto;

import java.util.List;

public interface AreaPersistencePort {

    AreaDto save(AreaDto area);

    List<AreaDto> getAll();

    AreaDto update(AreaDto area);

    void deleteById(Byte areaId);

}
