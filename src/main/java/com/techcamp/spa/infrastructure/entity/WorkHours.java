package com.techcamp.spa.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "WORK_HOURS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WORK_HOURS_ID")
    private Byte workHoursId;
    @Column(name = "HOURS", nullable = false)
    private Byte hours;
}
