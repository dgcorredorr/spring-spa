package com.techcamp.spa.domain.ports.spi;

import com.techcamp.spa.domain.data.AppointmentDto;

import java.util.List;

public interface AppointmentPersistencePort {

    AppointmentDto save(AppointmentDto appointment);

    List<AppointmentDto> getAll();

    AppointmentDto getById(Long appointmentId);

    AppointmentDto update(AppointmentDto appointment);

    void deleteById(Long appointmentId);

}
