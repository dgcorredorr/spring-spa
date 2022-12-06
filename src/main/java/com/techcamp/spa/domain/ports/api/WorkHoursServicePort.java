package com.techcamp.spa.domain.ports.api;

import com.techcamp.spa.domain.data.WorkHoursDto;

import java.util.List;

public interface WorkHoursServicePort {

    WorkHoursDto save(WorkHoursDto workHours);

    List<WorkHoursDto> getAll();

    WorkHoursDto update(WorkHoursDto workHours);

    void deleteById(Byte workHoursId);

}
