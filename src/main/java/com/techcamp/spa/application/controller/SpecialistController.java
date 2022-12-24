package com.techcamp.spa.application.controller;

import com.techcamp.spa.domain.data.SpecialistDto;
import com.techcamp.spa.domain.ports.api.SpecialistServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/specialist")
@CrossOrigin(origins = "${controller.cross-origins-path}")
public class SpecialistController {

    private final SpecialistServicePort specialistServicePort;

    @Autowired
    public SpecialistController(SpecialistServicePort specialistServicePort) {
        this.specialistServicePort = specialistServicePort;
    }

    @GetMapping
    public ResponseEntity<?> read(@RequestParam(required = false) Short specialistId,
                                  @RequestParam(required = false) String specialistName,
                                  @RequestParam(required = false) String area,
                                  Pageable pageable) {
        try {
            if (specialistId != null ^ (specialistName != null || area != null)) {
                if (specialistId != null) {
                    return new ResponseEntity<>(specialistServicePort.getById(specialistId), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(specialistServicePort.getByNameContainingAndAreaContaining(specialistName, area, pageable), HttpStatus.OK);
                }
            } else if (specialistId == null) {
                return new ResponseEntity<>(specialistServicePort.getAll(pageable), HttpStatus.OK);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron especialistas en la base de datos");
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No combinar specialistId con otro parámetro");
        }
    }

    @PostMapping
    public ResponseEntity<SpecialistDto> create(@RequestBody SpecialistDto specialist) {
        try {
            SpecialistDto specialistSaved = specialistServicePort.save(specialist);
            return new ResponseEntity<>(specialistServicePort.getById(specialistSaved.getSpecialistId()), HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se guardó el especialista en la base de datos");
        } catch (DataIntegrityViolationException e) {
            dataIntegrityViolationExceptionHandler(e);
            return null;
        }
    }

    @PutMapping
    public ResponseEntity<SpecialistDto> update(@RequestBody SpecialistDto specialist) {
        try {
            SpecialistDto specialistUpdated = specialistServicePort.update(specialist);
            return new ResponseEntity<>(specialistServicePort.getById(specialistUpdated.getSpecialistId()), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el especialista en la base de datos");
        } catch (DataIntegrityViolationException e) {
            dataIntegrityViolationExceptionHandler(e);
            return null;
        }
    }

    @DeleteMapping("/{specialistId}")
    public ResponseEntity<Object> deleteById(@PathVariable("specialistId") Short specialistId) {
        try {
            specialistServicePort.deleteById(specialistId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el especialista en la base de datos");
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody SpecialistDto specialist) {
        try {
            specialistServicePort.delete(specialist);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el especialista en la base de datos");
        } catch (DataIntegrityViolationException e) {
            dataIntegrityViolationExceptionHandler(e);
            return null;
        }
    }

    private void dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) throws ResponseStatusException {
        if (e.toString().contains("PropertyValueException")) {
            if (e.toString().contains("genderId")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Género no ingresado");
            } else if (e.toString().contains("areaId")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Especialidad no ingresada");
            } else if (e.toString().contains("workHoursId")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Horas laborales no ingresadas");
            } else if (e.toString().contains("firstName")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Primer nombre no ingresado");
            } else if (e.toString().contains("lastName")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Apellido no ingresado");
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Restricción violada: " + e.getMessage());
            }
        } else if (e.toString().contains("FK_SPECIALIST_GENDER")) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Referencia a genderId no existente");
        } else if (e.toString().contains("FK_SPECIALIST_AREA")) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Referencia a areaId no existente");
        } else if (e.toString().contains("FK_SPECIALIST_WORK_HOURS")) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Referencia a workHoursId no existente");
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Restricción violada: " + e.getMessage());
        }
    }

}
