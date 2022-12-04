package com.techcamp.spa.domain.ports.api;

import com.techcamp.spa.domain.data.DocumentTypeDto;

import java.util.List;

public interface DocumentTypeServicePort {

    DocumentTypeDto save(DocumentTypeDto documentType);

    List<DocumentTypeDto> getAll();

    DocumentTypeDto update(DocumentTypeDto documentType);

    Boolean deleteById(Byte documentTypeId);

}
