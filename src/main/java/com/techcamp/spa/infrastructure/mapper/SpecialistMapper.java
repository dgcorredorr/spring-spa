package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.SpecialistDto;
import com.techcamp.spa.infrastructure.entity.Specialist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecialistMapper extends Mapper<SpecialistDto, Specialist> {

    private final GenderMapper genderMapper;
    private final AreaMapper areaMapper;
    private final WorkHoursMapper workHoursMapper;

    @Autowired
    public SpecialistMapper(GenderMapper genderMapper, AreaMapper areaMapper, WorkHoursMapper workHoursMapper) {
        this.genderMapper = genderMapper;
        this.areaMapper = areaMapper;
        this.workHoursMapper = workHoursMapper;
    }

    @Override
    public SpecialistDto toDomain(Specialist specialist) {
        return (specialist != null) ? SpecialistDto.builder()
                .specialistId(specialist.getSpecialistId())
                .genderId(specialist.getGenderId())
                .areaId(specialist.getAreaId())
                .workHoursId(specialist.getWorkHoursId())
                .firstName(specialist.getFirstName())
                .middleName(specialist.getMiddleName())
                .lastName(specialist.getLastName())
                .gender(genderMapper.toDomain(specialist.getGender()))
                .area(areaMapper.toDomain(specialist.getArea()))
                .workHours(workHoursMapper.toDomain(specialist.getWorkHours()))
                .build() : null;
    }

    @Override
    public Specialist toEntity(SpecialistDto specialist) {
        return (specialist != null) ? Specialist.builder()
                .specialistId(specialist.getSpecialistId())
                .genderId(specialist.getGenderId())
                .areaId(specialist.getAreaId())
                .workHoursId(specialist.getWorkHoursId())
                .firstName(specialist.getFirstName())
                .middleName(specialist.getMiddleName())
                .lastName(specialist.getLastName())
                .build() : null;
    }
}
