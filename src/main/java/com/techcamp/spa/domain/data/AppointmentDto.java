package com.techcamp.spa.domain.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {
    private Long appointmentId;
    private Byte planId;
    private PlanDto plan;
    private Integer totalFee;
    private List<SessionInfoDto> sessions;
}
