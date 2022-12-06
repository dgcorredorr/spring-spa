package com.techcamp.spa.domain.ports.api;

import com.techcamp.spa.domain.data.AreaDto;

import java.util.List;

public interface AreaServicePort {

    AreaDto save(AreaDto area);

    List<AreaDto> getAll();

    AreaDto update(AreaDto area);

    void deleteById(Byte areaId);

}
