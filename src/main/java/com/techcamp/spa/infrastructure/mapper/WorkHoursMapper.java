package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.WorkHoursDto;
import com.techcamp.spa.infrastructure.entity.WorkHours;
import org.springframework.stereotype.Component;

@Component
public class WorkHoursMapper extends Mapper<WorkHoursDto, WorkHours>{
    @Override
    public WorkHoursDto toDomain(WorkHours workHours) {
        return (workHours != null) ? WorkHoursDto.builder()
                .workHoursId(workHours.getWorkHoursId())
                .hours(workHours.getHours())
                .build() : null;
    }

    @Override
    public WorkHours toEntity(WorkHoursDto workHours) {
        return (workHours != null) ? WorkHours.builder()
                .workHoursId(workHours.getWorkHoursId())
                .hours(workHours.getHours())
                .build() : null;
    }
}
