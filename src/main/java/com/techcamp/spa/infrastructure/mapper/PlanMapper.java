package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.PlanDto;
import com.techcamp.spa.infrastructure.entity.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper extends Mapper<PlanDto, Plan> {

    private final AreaMapper areaMapper;

    @Autowired
    public PlanMapper(AreaMapper areaMapper) {
        this.areaMapper = areaMapper;
    }
    @Override
    public PlanDto toDomain(Plan plan) {
        return (plan != null) ? PlanDto.builder()
                .planId(plan.getPlanId())
                .description(plan.getDescription())
                .capacity(plan.getCapacity())
                .discount(plan.getDiscount())
                .areas(areaMapper.toDomainList(plan.getAreas()))
                .build() : null;
    }

    @Override
    public Plan toEntity(PlanDto plan) {
        return (plan != null) ? Plan.builder()
                .planId(plan.getPlanId())
                .description(plan.getDescription())
                .capacity(plan.getCapacity())
                .discount(plan.getDiscount())
                .areas(areaMapper.toEntityList(plan.getAreas()))
                .build() : null;
    }
}
