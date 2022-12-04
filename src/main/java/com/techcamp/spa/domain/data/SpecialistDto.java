package com.techcamp.spa.domain.data;

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
    private Byte genderId;
    private GenderDto gender;
    private Byte areaId;
    private AreaDto area;
    private Byte workHoursId;
    private WorkHoursDto workHours;
    private String firstName;
    private String middleName;
    private String lastName;
}
