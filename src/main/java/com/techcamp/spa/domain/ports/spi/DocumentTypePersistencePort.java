package com.techcamp.spa.domain.ports.spi;

import com.techcamp.spa.domain.data.DocumentTypeDto;

import java.util.List;

public interface DocumentTypePersistencePort {

    DocumentTypeDto save(DocumentTypeDto documentType);

    List<DocumentTypeDto> getAll();

    DocumentTypeDto update(DocumentTypeDto documentType);

    void deleteById(Byte documentTypeId);

}
