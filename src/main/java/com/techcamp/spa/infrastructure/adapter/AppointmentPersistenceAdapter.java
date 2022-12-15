package com.techcamp.spa.infrastructure.adapter;

import com.techcamp.spa.domain.data.AppointmentDto;
import com.techcamp.spa.domain.ports.spi.AppointmentPersistencePort;
import com.techcamp.spa.infrastructure.mapper.AppointmentMapper;
import com.techcamp.spa.infrastructure.repository.AppointmentJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AppointmentPersistenceAdapter implements AppointmentPersistencePort {

    @Autowired
    private AppointmentJpaRepository appointmentJpaRepository;

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public AppointmentDto save(AppointmentDto appointmentDto) throws DataIntegrityViolationException {
        return appointmentMapper.toDomain(appointmentJpaRepository.save(appointmentMapper.toEntity(appointmentDto)));
    }

    @Override
    public Page<AppointmentDto> getAll(Pageable pageable) throws NoSuchElementException {
    Page<AppointmentDto> appointmentPage = appointmentMapper.toDomainPage(appointmentJpaRepository.findAll(pageable));
        if (appointmentPage.isEmpty()) {
            throw new NoSuchElementException("No se encontraron agendamientos en la base de datos");
        }
        return appointmentPage;
    }

    @Override
    public AppointmentDto getById(Long appointmentId) throws NoSuchElementException {
        return appointmentMapper.toDomain(appointmentJpaRepository.findById(appointmentId).orElseThrow(() ->
                new NoSuchElementException("No existe el agendamiento en la base de datos")));
    }

    @Override
    public AppointmentDto update(AppointmentDto appointmentDto) throws NoSuchElementException {
        if (appointmentDto.getAppointmentId() == null) {
            throw new DataIntegrityViolationException("AppointmentId no ingresado");
        } else if (appointmentJpaRepository.existsById(appointmentDto.getAppointmentId())) {
            return save(appointmentDto);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(Long appointmentId) throws NoSuchElementException {
        if (appointmentJpaRepository.existsById(appointmentId)) {
            appointmentJpaRepository.deleteById(appointmentId);
        } else {
            throw new NoSuchElementException();
        }
    }
}
