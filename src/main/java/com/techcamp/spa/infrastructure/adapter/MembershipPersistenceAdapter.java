package com.techcamp.spa.infrastructure.adapter;

import com.techcamp.spa.domain.data.MembershipDto;
import com.techcamp.spa.domain.ports.spi.MembershipPersistencePort;
import com.techcamp.spa.infrastructure.mapper.MembershipMapper;
import com.techcamp.spa.infrastructure.repository.MembershipJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MembershipPersistenceAdapter implements MembershipPersistencePort {
    
    @Autowired
    private MembershipJpaRepository membershipJpaRepository;
    
    @Autowired
    private MembershipMapper membershipMapper;
    
    @Override
    public MembershipDto save(MembershipDto membership) throws DataIntegrityViolationException {
        return membershipMapper.toDomain(membershipJpaRepository.save(membershipMapper.toEntity(membership)));
    }

    @Override
    public List<MembershipDto> getAll() {
        List<MembershipDto> membershipList = membershipMapper.toDomainList(membershipJpaRepository.findAll());
        if (membershipList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return membershipList;
    }

    @Override
    public MembershipDto update(MembershipDto membershipDto) throws NoSuchElementException {
        if (membershipDto.getMembershipId() == null) {
            throw new DataIntegrityViolationException("MembershipId no ingresado");
        } else if (membershipJpaRepository.existsById(membershipDto.getMembershipId())) {
            return save(membershipDto);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(Byte membershipId) throws NoSuchElementException {
        if (membershipJpaRepository.existsById(membershipId)) {
            membershipJpaRepository.deleteById(membershipId);
        } else {
            throw new NoSuchElementException();
        }
    }
}
