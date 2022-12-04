package com.techcamp.spa.domain.ports.spi;

import com.techcamp.spa.domain.data.MembershipDto;

import java.util.List;

public interface MembershipPersistencePort {

    MembershipDto save(MembershipDto membership);

    List<MembershipDto> getAll();

    MembershipDto update(MembershipDto membership);

    Boolean deleteById(Byte membershipId);

}
