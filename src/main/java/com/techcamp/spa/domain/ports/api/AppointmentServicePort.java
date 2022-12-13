package com.techcamp.spa.domain.ports.api;

import com.techcamp.spa.domain.data.AppointmentDto;

import java.util.List;

public interface AppointmentServicePort {

    AppointmentDto save(AppointmentDto appointment);

    List<AppointmentDto> getAll();

    AppointmentDto getById(Long appointmentId);

    AppointmentDto update(AppointmentDto appointment);

    void deleteById(Long appointmentId);

}
