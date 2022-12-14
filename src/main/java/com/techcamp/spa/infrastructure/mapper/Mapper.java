package com.techcamp.spa.infrastructure.mapper;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;


public abstract class Mapper<D, E> {

    public abstract D toDomain(E entity);

    public abstract E toEntity(D dto);

    public List<D> toDomainList(List<E> entityList) {
        return (entityList != null) ? entityList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList()) : null;
    }

    public List<E> toEntityList(List<D> dtoList) {
        return (dtoList != null) ? dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList()) : null;
    }

    public Page<D> toDomainPage(Page<E> entityPage) {
        return entityPage.map(this::toDomain);
    }
}
