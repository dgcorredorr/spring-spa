package com.techcamp.spa.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SPECIALIST")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Specialist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SPECIALIST_ID")
    private Short specialistId;
    @Column(name = "GENDER_ID", nullable = false)
    private Byte genderId;
    @Column(name = "AREA_ID", nullable = false)
    private Byte areaId;
    @Column(name = "WORK_HOURS_ID", nullable = false)
    private Byte workHoursId;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "GENDER_ID", insertable = false, updatable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "AREA_ID", insertable = false, updatable = false)
    private Area area;

    @ManyToOne
    @JoinColumn(name = "WORK_HOURS_ID", insertable = false, updatable = false)
    private WorkHours workHours;
}
