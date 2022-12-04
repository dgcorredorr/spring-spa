package com.techcamp.spa.domain.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Byte planId;
    private PlanDto plan;
    private Integer totalFee;
    private List<SessionInfoDto> sessions;
}
