package com.techcamp.spa.domain.service;

import com.techcamp.spa.domain.data.DocumentTypeDto;
import com.techcamp.spa.domain.ports.api.DocumentTypeServicePort;
import com.techcamp.spa.domain.ports.spi.DocumentTypePersistencePort;

import java.util.List;

public class DocumentTypeServiceImpl implements DocumentTypeServicePort {

    private final DocumentTypePersistencePort documentTypePersistencePort;

    public DocumentTypeServiceImpl(DocumentTypePersistencePort documentTypePersistencePort) {
        this.documentTypePersistencePort = documentTypePersistencePort;
    }

    @Override
    public DocumentTypeDto save(DocumentTypeDto documentType) {
        return documentTypePersistencePort.save(documentType);
    }

    @Override
    public List<DocumentTypeDto> getAll() {
        return documentTypePersistencePort.getAll();
    }

    @Override
    public DocumentTypeDto update(DocumentTypeDto documentType) {
        return documentTypePersistencePort.update(documentType);
    }

    @Override
    public void deleteById(Byte documentTypeId) {
        documentTypePersistencePort.deleteById(documentTypeId);
    }
}
