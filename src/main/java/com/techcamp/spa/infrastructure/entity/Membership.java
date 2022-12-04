package com.techcamp.spa.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MEMBERSHIP")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBERSHIP_ID")
    private Byte membershipId;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "DISCOUNT", nullable = false)
    private Byte discount;
    @Column(name = "MONTHLY_FEE", nullable = false)
    private Integer monthlyFee;
}
