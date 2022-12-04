package com.techcamp.spa.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CLIENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID")
    private Long clientId;
    @Column(name = "GENDER_ID", nullable = false)
    private Byte genderId;
    @Column(name = "DOCUMENT_TYPE_ID", nullable = false)
    private Byte documentTypeId;
    @Column(name = "MEMBERSHIP_ID", nullable = false)
    private Byte membershipId;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    @Column(name = "DOCUMENT_NUMBER", nullable = false, unique = true)
    private String documentNumber;
    @Column(name = "BIRTH_DATE", columnDefinition = "DATE", nullable = false)
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "GENDER_ID", insertable = false, updatable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "DOCUMENT_TYPE_ID", insertable = false, updatable = false)
    private DocumentType documentType;

    @ManyToOne
    @JoinColumn(name = "MEMBERSHIP_ID", insertable = false, updatable = false)
    private Membership membership;
}
