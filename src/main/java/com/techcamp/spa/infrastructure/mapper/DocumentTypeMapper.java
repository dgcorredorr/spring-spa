package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.DocumentTypeDto;
import com.techcamp.spa.infrastructure.entity.DocumentType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DocumentTypeMapper {
    public DocumentTypeDto toDomain(DocumentType documentType) {
        return (documentType != null) ? DocumentTypeDto.builder()
                .documentTypeId(documentType.getDocumentTypeId())
                .abbreviation(documentType.getAbbreviation())
                .description(documentType.getDescription())
                .build() : null;
    }

    public DocumentType toEntity(DocumentTypeDto documentType) {
        return (documentType != null) ? DocumentType.builder()
                .documentTypeId(documentType.getDocumentTypeId())
                .abbreviation(documentType.getAbbreviation())
                .description(documentType.getDescription())
                .build() : null;
    }

    public List<DocumentTypeDto> toDomainList(List<DocumentType> documentTypeList) {
        return documentTypeList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<DocumentType> toEntityList(List<DocumentTypeDto> documentTypeList) {
        return documentTypeList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
