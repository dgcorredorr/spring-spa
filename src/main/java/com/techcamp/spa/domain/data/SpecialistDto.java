package com.techcamp.spa.domain.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecialistDto {
    private Short specialistId;
    @JsonIgnore
    private Byte genderId;
    private GenderDto gender;
    @JsonIgnore
    private Byte areaId;
    private AreaDto area;
    @JsonIgnore
    private Byte workHoursId;
    private WorkHoursDto workHours;
    private String firstName;
    private String middleName;
    private String lastName;
}
