package com.techcamp.spa.infrastructure.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DOCUMENT_TYPE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOCUMENT_TYPE_ID")
    private Byte documentTypeId;
    @Column(name = "ABBREVIATION", nullable = false)
    private String abbreviation;
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
}
