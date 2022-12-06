package com.techcamp.spa.infrastructure.adapter;

import com.techcamp.spa.domain.data.AreaDto;
import com.techcamp.spa.domain.ports.spi.AreaPersistencePort;
import com.techcamp.spa.infrastructure.mapper.AreaMapper;
import com.techcamp.spa.infrastructure.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AreaJpaAdapter implements AreaPersistencePort {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public AreaDto save(AreaDto areaDto) throws DataIntegrityViolationException {
        return areaMapper.toDomain(areaRepository.save(areaMapper.toEntity(areaDto)));
    }

    @Override
    public List<AreaDto> getAll() {
        List<AreaDto> areaList = areaMapper.toDomainList(areaRepository.findAll());
        if (areaList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return areaList;
    }

    @Override
    public AreaDto update(AreaDto areaDto) throws NoSuchElementException {
        if (areaDto.getAreaId() == null) {
            throw new DataIntegrityViolationException("AreaId no ingresado");
        } else if (areaRepository.existsById(areaDto.getAreaId())) {
            return save(areaDto);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(Byte areaId) throws NoSuchElementException {
        if (areaRepository.existsById(areaId)) {
            areaRepository.deleteById(areaId);
        } else {
            throw new NoSuchElementException();
        }
    }
}
