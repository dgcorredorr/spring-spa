package com.techcamp.spa.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "AREA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AREA_ID")
    private Byte areaId;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "SESSION_FEE", nullable = false)
    private Integer sessionFee;


}
