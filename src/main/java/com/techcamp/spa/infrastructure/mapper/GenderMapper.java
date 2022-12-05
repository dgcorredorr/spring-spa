package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.GenderDto;
import com.techcamp.spa.infrastructure.entity.Gender;
import org.springframework.stereotype.Component;

@Component
public class GenderMapper extends Mapper<GenderDto, Gender> {
    @Override
    public GenderDto toDomain(Gender gender) {
        return (gender != null) ? GenderDto.builder()
                .genderId(gender.getGenderId())
                .description(gender.getDescription())
                .build() : null;
    }

    @Override
    public Gender toEntity(GenderDto gender) {
        return (gender != null) ? Gender.builder()
                .genderId(gender.getGenderId())
                .description(gender.getDescription())
                .build() : null;
    }

}
