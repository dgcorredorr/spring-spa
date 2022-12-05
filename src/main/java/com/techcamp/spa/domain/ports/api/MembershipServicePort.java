package com.techcamp.spa.domain.ports.api;

import com.techcamp.spa.domain.data.MembershipDto;

import java.util.List;

public interface MembershipServicePort {
    
    MembershipDto save(MembershipDto membership);

    List<MembershipDto> getAll();

    MembershipDto update(MembershipDto membership);

    void deleteById(Byte membershipId);
    
}
