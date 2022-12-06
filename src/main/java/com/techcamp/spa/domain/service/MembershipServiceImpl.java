package com.techcamp.spa.domain.service;

import com.techcamp.spa.domain.data.MembershipDto;
import com.techcamp.spa.domain.ports.api.MembershipServicePort;
import com.techcamp.spa.domain.ports.spi.MembershipPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MembershipServiceImpl implements MembershipServicePort {

    private final MembershipPersistencePort membershipPersistencePort;

    @Autowired
    public MembershipServiceImpl(MembershipPersistencePort membershipPersistencePort) {
        this.membershipPersistencePort = membershipPersistencePort;
    }

    @Override
    public MembershipDto save(MembershipDto membership) {
        return membershipPersistencePort.save(membership);
    }

    @Override
    public List<MembershipDto> getAll() {
        return membershipPersistencePort.getAll();
    }

    @Override
    public MembershipDto update(MembershipDto membership) {
        return membershipPersistencePort.update(membership);
    }

    @Override
    public void deleteById(Byte membershipId) {
        membershipPersistencePort.deleteById(membershipId);
    }
}

