package com.techcamp.spa.domain.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionInfoDto {
    private Long sessionInfoId;
    private Short specialistId;
    private SpecialistDto specialist;
    private Long clientId;
    private ClientDto client;
    private Long appointmentId;
    private AppointmentDto appointment;
    private LocalDateTime sessionDate;
}
