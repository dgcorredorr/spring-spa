package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.AreaDto;
import com.techcamp.spa.infrastructure.entity.Area;
import org.springframework.stereotype.Component;

@Component
public class AreaMapper extends Mapper<AreaDto, Area>{
    @Override
    public AreaDto toDomain(Area area) {
        return (area != null) ? AreaDto.builder()
                .areaId(area.getAreaId())
                .description(area.getDescription())
                .sessionFee(area.getSessionFee())
                .build() : null;
    }

    @Override
    public Area toEntity(AreaDto area) {
        return (area != null) ? Area.builder()
                .areaId(area.getAreaId())
                .description(area.getDescription())
                .sessionFee(area.getSessionFee())
                .build() : null;
    }
}
