package com.techcamp.spa.infrastructure.adapter;

import com.techcamp.spa.domain.data.DocumentTypeDto;
import com.techcamp.spa.domain.ports.spi.DocumentTypePersistencePort;
import com.techcamp.spa.infrastructure.mapper.DocumentTypeMapper;
import com.techcamp.spa.infrastructure.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DocumentTypeJpaAdapter implements DocumentTypePersistencePort {
    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private DocumentTypeMapper documentTypeMapper;

    @Override
    public DocumentTypeDto save(DocumentTypeDto documentTypeDto) throws DataIntegrityViolationException {
        return documentTypeMapper.toDomain(documentTypeRepository.save(documentTypeMapper.toEntity(documentTypeDto)));
    }

    @Override
    public List<DocumentTypeDto> getAll() {
        List<DocumentTypeDto> documentTypeList = documentTypeMapper.toDomainList(documentTypeRepository.findAll());
        if (documentTypeList.isEmpty()) {
            throw new NoSuchElementException();
        }
        return documentTypeList;
    }

    @Override
    public DocumentTypeDto update(DocumentTypeDto documentTypeDto) throws NoSuchElementException {
        if (documentTypeDto.getDocumentTypeId() == null) {
            throw new DataIntegrityViolationException("DocumentTypeId no ingresado");
        } else if (documentTypeRepository.existsById(documentTypeDto.getDocumentTypeId())) {
            return save(documentTypeDto);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(Byte documentTypeId) throws NoSuchElementException {
        if (documentTypeRepository.existsById(documentTypeId)) {
            documentTypeRepository.deleteById(documentTypeId);
        } else {
            throw new NoSuchElementException();
        }
    }
}
