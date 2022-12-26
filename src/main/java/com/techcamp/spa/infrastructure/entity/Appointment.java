package com.techcamp.spa.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "APPOINTMENT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPOINTMENT_ID")
    private Long appointmentId;
    @Column(name = "PLAN_ID", nullable = false)
    private Byte planId;
    @Column(name = "APPOINTMENT_DATE", nullable = false)
    private LocalDate appointmentDate;
    @Column(name = "TOTAL_FEE")
    private Integer totalFee;

    @ManyToOne
    @JoinColumn(name = "PLAN_ID", insertable = false, updatable = false)
    private Plan plan;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "CLIENT_APPOINTMENT",
            joinColumns = @JoinColumn(name = "APPOINTMENT_ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "CLIENT_ID", nullable = false)
    )
    private List<Client> clientList;
}
