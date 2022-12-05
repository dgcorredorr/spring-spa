package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.DocumentTypeDto;
import com.techcamp.spa.infrastructure.entity.DocumentType;
import org.springframework.stereotype.Component;

@Component
public class DocumentTypeMapper extends Mapper<DocumentTypeDto, DocumentType> {
    @Override
    public DocumentTypeDto toDomain(DocumentType documentType) {
        return (documentType != null) ? DocumentTypeDto.builder()
                .documentTypeId(documentType.getDocumentTypeId())
                .abbreviation(documentType.getAbbreviation())
                .description(documentType.getDescription())
                .build() : null;
    }

    @Override
    public DocumentType toEntity(DocumentTypeDto documentType) {
        return (documentType != null) ? DocumentType.builder()
                .documentTypeId(documentType.getDocumentTypeId())
                .abbreviation(documentType.getAbbreviation())
                .description(documentType.getDescription())
                .build() : null;
    }

}
