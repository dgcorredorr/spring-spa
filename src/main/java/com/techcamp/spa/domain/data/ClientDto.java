package com.techcamp.spa.domain.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
    private Long clientId;
    private Byte genderId;
    private GenderDto gender;
    private Byte documentTypeId;
    private DocumentTypeDto documentType;
    private Byte membershipId;
    private MembershipDto membership;
    private String firstName;
    private String middleName;
    private String lastName;
    private String documentNumber;
    private LocalDate birthDate;

}
