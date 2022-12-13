package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.AppointmentDto;
import com.techcamp.spa.infrastructure.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper extends Mapper<AppointmentDto, Appointment> {

    private final PlanMapper planMapper;

    @Autowired
    public AppointmentMapper(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }

    @Override
    public AppointmentDto toDomain(Appointment appointment) {
        return (appointment != null) ? AppointmentDto.builder()
                .appointmentId(appointment.getAppointmentId())
                .planId(appointment.getPlanId())
                .totalFee(appointment.getTotalFee())
                .plan(planMapper.toDomain(appointment.getPlan()))
                .build() : null;
    }

    @Override
    public Appointment toEntity(AppointmentDto appointment) {
        return (appointment != null) ? Appointment.builder()
                .appointmentId(appointment.getAppointmentId())
                .planId(appointment.getPlanId())
                .totalFee(appointment.getTotalFee())
                .build() : null;
    }
}
