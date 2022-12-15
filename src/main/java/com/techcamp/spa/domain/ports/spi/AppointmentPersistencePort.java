package com.techcamp.spa.domain.ports.spi;

import com.techcamp.spa.domain.data.AppointmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppointmentPersistencePort {

    AppointmentDto save(AppointmentDto appointment);

    Page<AppointmentDto> getAll(Pageable pageable);

    AppointmentDto getById(Long appointmentId);

    AppointmentDto update(AppointmentDto appointment);

    void deleteById(Long appointmentId);

}
