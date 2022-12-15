package com.techcamp.spa.domain.ports.api;

import com.techcamp.spa.domain.data.AppointmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppointmentServicePort {

    AppointmentDto save(AppointmentDto appointment);

    Page<AppointmentDto> getAll(Pageable pageable);

    AppointmentDto getById(Long appointmentId);

    AppointmentDto update(AppointmentDto appointment);

    void deleteById(Long appointmentId);

}
