package com.techcamp.spa.application.controller;

import com.techcamp.spa.domain.data.ClientDto;
import com.techcamp.spa.domain.ports.api.ClientServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientServicePort clientServicePort;

    @Autowired
    public ClientController(ClientServicePort clientServicePort) {
        this.clientServicePort = clientServicePort;
    }

    @GetMapping
    public ResponseEntity<?> read(@RequestParam(required = false) Long clientId,
                                  @RequestParam(required = false) String clientName,
                                  @RequestParam(required = false) String documentNumber,
                                  @PageableDefault(size = 20) Pageable pageable) {
        try {
            if (clientId != null ^ (clientName != null || documentNumber != null)) {
                if (clientId != null) {
                    return new ResponseEntity<>(clientServicePort.getById(clientId), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(clientServicePort.getByNameContainingAndDocumentNumberContaining(clientName, documentNumber, pageable), HttpStatus.OK);
                }
            } else if (clientId == null) {
                return new ResponseEntity<>(clientServicePort.getAll(pageable), HttpStatus.OK);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (InvalidDataAccessResourceUsageException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parámetro de ordenamiento inválido");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron clientes en la base de datos");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No combinar clientId con otro parámetro");
        }
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto client) {
        try {
            ClientDto clientSaved = clientServicePort.save(client);
            return new ResponseEntity<>(clientServicePort.getById(clientSaved.getClientId()), HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se guardó el cliente en la base de datos");
        } catch (DataIntegrityViolationException e) {
            dataIntegrityViolationExceptionHandler(e);
            return null;
        }
    }

    @PutMapping
    public ResponseEntity<ClientDto> update(@RequestBody ClientDto client) {
        try {
            ClientDto clientUpdated = clientServicePort.update(client);
            return new ResponseEntity<>(clientServicePort.getById(clientUpdated.getClientId()), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el cliente en la base de datos");
        } catch (DataIntegrityViolationException e) {
            dataIntegrityViolationExceptionHandler(e);
            return null;
        }
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<?> deleteById(@PathVariable("clientId") Long clientId) {
        try {
            clientServicePort.deleteById(clientId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el cliente en la base de datos");
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody ClientDto client) {
        try {
            clientServicePort.delete(client);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el cliente en la base de datos");
        } catch (DataIntegrityViolationException e) {
            dataIntegrityViolationExceptionHandler(e);
            return null;
        }
    }

    private void dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) throws ResponseStatusException {
        if (e.toString().contains("UQ_CLIENT_DOCUMENT_NUMBER")) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Número de documento ya existente");
        } else if (e.toString().contains("PropertyValueException")) {
            if (e.toString().contains("genderId")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Género no ingresado");
            } else if (e.toString().contains("documentTypeId")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Tipo de documento no ingresado");
            } else if (e.toString().contains("membershipId")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Membresía no ingresada");
            } else if (e.toString().contains("firstName")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Primer nombre no ingresado");
            } else if (e.toString().contains("lastName")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Apellido no ingresado");
            } else if (e.toString().contains("documentNumber")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Número de documento no ingresado");
            } else if (e.toString().contains("birthDate")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Fecha de nacimiento no ingresada");
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Restricción violada: " + e.getMessage());
            }
        } else if (e.toString().contains("FK_CLIENT_GENDER")) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Referencia a genderId no existente");
        } else if (e.toString().contains("FK_CLIENT_DOCUMENT_TYPE")) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Referencia a documentTypeId no existente");
        } else if (e.toString().contains("FK_CLIENT_MEMBERSHIP")) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Referencia a membershipId no existente");
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Restricción violada: " + e.getMessage());
        }
    }

}
