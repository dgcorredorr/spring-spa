package com.techcamp.spa.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PLAN")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAN_ID")
    private Byte planId;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    @Column(name = "CAPACITY", nullable = false)
    private Byte capacity;
    @Column(name = "DISCOUNT", nullable = false)
    private Byte discount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "AREA_PLAN",
            joinColumns = @JoinColumn(name = "PLAN_ID", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "AREA_ID", nullable = false)
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Area> areas;

}
