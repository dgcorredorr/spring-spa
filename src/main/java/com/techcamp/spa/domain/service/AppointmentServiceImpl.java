package com.techcamp.spa.domain.service;

import com.techcamp.spa.domain.data.AppointmentDto;
import com.techcamp.spa.domain.ports.api.AppointmentServicePort;
import com.techcamp.spa.domain.ports.spi.AppointmentPersistencePort;

import java.util.List;

public class AppointmentServiceImpl implements AppointmentServicePort {

    private final AppointmentPersistencePort appointmentPersistencePort;

    public AppointmentServiceImpl(AppointmentPersistencePort appointmentPersistencePort) {
        this.appointmentPersistencePort = appointmentPersistencePort;
    }

    @Override
    public AppointmentDto save(AppointmentDto appointment) {
        return appointmentPersistencePort.save(appointment);
    }

    @Override
    public List<AppointmentDto> getAll() {
        return appointmentPersistencePort.getAll();
    }

    @Override
    public AppointmentDto getById(Long appointmentId) {
        return appointmentPersistencePort.getById(appointmentId);
    }

    @Override
    public AppointmentDto update(AppointmentDto appointment) {
        return appointmentPersistencePort.update(appointment);
    }

    @Override
    public void deleteById(Long appointmentId) {
        appointmentPersistencePort.deleteById(appointmentId);
    }
}
