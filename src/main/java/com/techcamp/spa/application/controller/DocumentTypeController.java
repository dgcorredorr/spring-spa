package com.techcamp.spa.application.controller;

import com.techcamp.spa.domain.data.DocumentTypeDto;
import com.techcamp.spa.domain.ports.api.DocumentTypeServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/document_type")
@CrossOrigin(origins = "http://localhost:4200")
public class DocumentTypeController {

    private final DocumentTypeServicePort documentTypeServicePort;

    @Autowired
    public DocumentTypeController(DocumentTypeServicePort documentTypeServicePort) {
        this.documentTypeServicePort = documentTypeServicePort;
    }

    @GetMapping
    public ResponseEntity<List<DocumentTypeDto>> read() {
        try {
            return new ResponseEntity<>(documentTypeServicePort.getAll(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron tipos de documento en la base de datos");
        }
    }
}
