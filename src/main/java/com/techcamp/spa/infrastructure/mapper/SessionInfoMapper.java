package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.SessionInfoDto;
import com.techcamp.spa.infrastructure.entity.SessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionInfoMapper extends Mapper<SessionInfoDto, SessionInfo>{

    private final SpecialistMapper specialistMapper;
    private final ClientMapper clientMapper;
    private final AppointmentMapper appointmentMapper;

    @Autowired
    public SessionInfoMapper(SpecialistMapper specialistMapper, ClientMapper clientMapper, AppointmentMapper appointmentMapper) {
        this.specialistMapper = specialistMapper;
        this.clientMapper = clientMapper;
        this.appointmentMapper = appointmentMapper;
    }

    @Override
    public SessionInfoDto toDomain(SessionInfo sessionInfo) {
        return (sessionInfo != null) ? SessionInfoDto.builder()
                .sessionInfoId(sessionInfo.getSessionInfoId())
                .specialistId(sessionInfo.getSpecialistId())
                .clientId(sessionInfo.getClientId())
                .appointmentId(sessionInfo.getAppointmentId())
                .sessionDate(sessionInfo.getSessionDate())
                .specialist(specialistMapper.toDomain(sessionInfo.getSpecialist()))
                .client(clientMapper.toDomain(sessionInfo.getClient()))
                .appointment(appointmentMapper.toDomain(sessionInfo.getAppointment()))
                .build() : null;
    }

    @Override
    public SessionInfo toEntity(SessionInfoDto sessionInfo) {
        return (sessionInfo != null) ? SessionInfo.builder()
                .sessionInfoId(sessionInfo.getSessionInfoId())
                .specialistId(sessionInfo.getSpecialistId())
                .clientId(sessionInfo.getClientId())
                .appointmentId(sessionInfo.getAppointmentId())
                .sessionDate(sessionInfo.getSessionDate())
                .build() : null;
    }
}
