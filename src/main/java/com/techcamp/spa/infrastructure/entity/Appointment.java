package com.techcamp.spa.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Column(name = "TOTAL_FEE")
    private Integer totalFee;

    @ManyToOne
    @JoinColumn(name = "PLAN_ID", insertable = false, updatable = false)
    private Plan plan;
}
