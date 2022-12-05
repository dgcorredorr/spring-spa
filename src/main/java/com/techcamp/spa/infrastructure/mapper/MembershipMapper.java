package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.MembershipDto;
import com.techcamp.spa.infrastructure.entity.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper extends Mapper<MembershipDto, Membership> {
    @Override
    public MembershipDto toDomain(Membership membership) {
        return (membership != null) ? MembershipDto.builder()
                .membershipId(membership.getMembershipId())
                .description(membership.getDescription())
                .discount(membership.getDiscount())
                .monthlyFee(membership.getMonthlyFee())
                .build() : null;
    }

    @Override
    public Membership toEntity(MembershipDto membership) {
        return (membership != null) ? Membership.builder()
                .membershipId(membership.getMembershipId())
                .description(membership.getDescription())
                .discount(membership.getDiscount())
                .monthlyFee(membership.getMonthlyFee())
                .build() : null;
    }

}
