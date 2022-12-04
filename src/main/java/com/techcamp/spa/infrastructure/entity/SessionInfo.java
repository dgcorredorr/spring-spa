package com.techcamp.spa.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SESSION_INFO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SessionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SESSION_INFO_ID")
    private Long sessionInfoId;
    @Column(name = "SPECIALIST_ID", nullable = false)
    private Short specialistId;
    @Column(name = "CLIENT_ID", nullable = false)
    private Long clientId;
    @Column(name = "APPOINTMENT_ID", nullable = false)
    private Long appointmentId;
    @Column(name = "SESSION_DATE", nullable = false)
    private LocalDateTime sessionDate;

    @ManyToOne
    @JoinColumn(name = "SPECIALIST_ID", insertable = false, updatable = false)
    private Specialist specialist;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", insertable = false, updatable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "APPOINTMENT_ID", insertable = false, updatable = false)
    private Appointment appointment;

}
