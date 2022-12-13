package com.techcamp.spa.infrastructure.adapter;

import com.techcamp.spa.domain.data.GenderDto;
import com.techcamp.spa.domain.ports.spi.GenderPersistencePort;
import com.techcamp.spa.infrastructure.mapper.GenderMapper;
import com.techcamp.spa.infrastructure.repository.GenderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GenderPersistenceAdapter implements GenderPersistencePort {

    @Autowired
    private GenderJpaRepository genderJpaRepository;

    @Autowired
    private GenderMapper genderMapper;

    @Override
    public GenderDto save(GenderDto genderDto) throws DataIntegrityViolationException {
        return genderMapper.toDomain(genderJpaRepository.save(genderMapper.toEntity(genderDto)));
    }

    @Override
    public List<GenderDto> getAll() {
        List<GenderDto> genderList = genderMapper.toDomainList(genderJpaRepository.findAll());
        if (genderList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return genderList;
    }

    @Override
    public GenderDto update(GenderDto genderDto) throws NoSuchElementException {
        if (genderDto.getGenderId() == null) {
            throw new DataIntegrityViolationException("GenderId no ingresado");
        } else if (genderJpaRepository.existsById(genderDto.getGenderId())) {
            return save(genderDto);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(Byte genderId) throws NoSuchElementException {
        if (genderJpaRepository.existsById(genderId)) {
            genderJpaRepository.deleteById(genderId);
        } else {
            throw new NoSuchElementException();
        }
    }
}
