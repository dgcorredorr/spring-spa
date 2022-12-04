package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.GenderDto;
import com.techcamp.spa.infrastructure.entity.Gender;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenderMapper {

    public GenderDto toDomain(Gender gender) {
        return (gender != null) ? GenderDto.builder()
                .genderId(gender.getGenderId())
                .description(gender.getDescription())
                .build() : null;
    }

    public Gender toEntity(GenderDto gender) {
        return (gender != null) ? Gender.builder()
                .genderId(gender.getGenderId())
                .description(gender.getDescription())
                .build() : null;
    }

    public List<GenderDto> toDomainList(List<Gender> genderList) {
        return genderList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<Gender> toEntityList(List<GenderDto> genderList) {
        return genderList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
