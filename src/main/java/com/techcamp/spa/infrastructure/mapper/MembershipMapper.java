package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.MembershipDto;
import com.techcamp.spa.infrastructure.entity.Membership;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MembershipMapper {
    public MembershipDto toDomain(Membership membership) {
        return (membership != null) ? MembershipDto.builder()
                .membershipId(membership.getMembershipId())
                .description(membership.getDescription())
                .discount(membership.getDiscount())
                .monthlyFee(membership.getMonthlyFee())
                .build() : null;
    }

    public Membership toEntity(MembershipDto membership) {
        return (membership != null) ? Membership.builder()
                .membershipId(membership.getMembershipId())
                .description(membership.getDescription())
                .discount(membership.getDiscount())
                .monthlyFee(membership.getMonthlyFee())
                .build() : null;
    }

    public List<MembershipDto> toDomainList(List<Membership> membershipList) {
        return membershipList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<Membership> toEntityList(List<MembershipDto> membershipList) {
        return membershipList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
