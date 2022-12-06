package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.AppointmentDto;
import com.techcamp.spa.infrastructure.entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper extends Mapper<AppointmentDto, Appointment> {

    private final PlanMapper planMapper;
    private final SessionInfoMapper sessionInfoMapper;

    @Autowired
    public AppointmentMapper(PlanMapper planMapper, SessionInfoMapper sessionInfoMapper) {
        this.planMapper = planMapper;
        this.sessionInfoMapper = sessionInfoMapper;
    }

    @Override
    public AppointmentDto toDomain(Appointment appointment) {
        return (appointment != null) ? AppointmentDto.builder()
                .appointmentId(appointment.getAppointmentId())
                .planId(appointment.getPlanId())
                .totalFee(appointment.getTotalFee())
                .plan(planMapper.toDomain(appointment.getPlan()))
                .sessions(sessionInfoMapper.toDomainList(appointment.getSessions()))
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
