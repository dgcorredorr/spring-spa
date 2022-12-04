package com.techcamp.spa.domain.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Short specialistId;
    private SpecialistDto specialist;
    @JsonIgnore
    private Long clientId;
    private ClientDto client;
    private LocalDateTime sessionDate;
}
