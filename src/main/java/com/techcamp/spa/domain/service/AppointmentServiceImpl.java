package com.techcamp.spa.domain.service;

import com.techcamp.spa.domain.data.AppointmentDto;
import com.techcamp.spa.domain.ports.api.AppointmentServicePort;
import com.techcamp.spa.domain.ports.spi.AppointmentPersistencePort;
import com.techcamp.spa.domain.ports.spi.PlanPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentServiceImpl implements AppointmentServicePort {

    private final AppointmentPersistencePort appointmentPersistencePort;

    private final PlanPersistencePort planPersistencePort;

    public AppointmentServiceImpl(AppointmentPersistencePort appointmentPersistencePort,
                                  PlanPersistencePort planPersistencePort) {
        this.appointmentPersistencePort = appointmentPersistencePort;
        this.planPersistencePort = planPersistencePort;
    }

    @Override
    public AppointmentDto save(AppointmentDto appointment) throws IllegalArgumentException {
        int planCapacity = planPersistencePort.getById(appointment.getPlanId()).getCapacity();
        int clientListSize = (appointment.getClientList()==null) ? 0 : appointment.getClientList().size();
        if (clientListSize == 0) {
            throw new IllegalArgumentException("La cantidad de clientes no puede ser cero");
        } else if (planCapacity >= clientListSize) {
            List<Long> clientIdList = new ArrayList<>();
            appointment.setClientList(appointment.getClientList()
                    .stream()
                    .map(clientDto -> {
                        Long clientId = clientDto.getClientId();
                        if (!clientIdList.contains(clientId)) {
                            clientIdList.add(clientId);
                            return clientDto;
                        } else {
                            throw new IllegalArgumentException("Clientes duplicados en el agendamiento");
                        }
                    })
                    .collect(Collectors.toList())
            );
            return appointmentPersistencePort.save(appointment);
        } else {
            throw new IllegalArgumentException("La cantidad de clientes excede la capacidad del plan agendado");
        }
    }

    @Override
    public Page<AppointmentDto> getAll(Pageable pageable) {
        return appointmentPersistencePort.getAll(pageable);
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
