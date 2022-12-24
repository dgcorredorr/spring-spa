package com.techcamp.spa.application.controller;

import com.techcamp.spa.domain.data.AppointmentDto;
import com.techcamp.spa.domain.ports.api.AppointmentServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "${controller.cross-origins-path}")
public class AppointmentController {

    private final AppointmentServicePort appointmentServicePort;

    @Autowired
    public AppointmentController(AppointmentServicePort appointmentServicePort) {
        this.appointmentServicePort = appointmentServicePort;
    }

    @GetMapping
    public ResponseEntity<?> read(@RequestParam(required = false) Long appointmentId,
                                  Pageable pageable) {
        try {
            if (appointmentId==null) {
                return new ResponseEntity<>(appointmentServicePort.getAll(pageable), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(appointmentServicePort.getById(appointmentId), HttpStatus.OK);
            }

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<AppointmentDto> create(@RequestBody AppointmentDto appointment) {
        try {
            if (appointment.getTotalFee() == null) {
                AppointmentDto appointmentSaved = appointmentServicePort.save(appointment);
                return new ResponseEntity<>(appointmentServicePort.getById(appointmentSaved.getAppointmentId()), HttpStatus.CREATED);
            } else {
                throw new IllegalArgumentException("No incluir el costo total en la petición");
            }

        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se guardó el agendamiento en la base de datos");
        } catch (DataIntegrityViolationException e) {
            dataIntegrityViolationExceptionHandler(e);
            return null;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<AppointmentDto> update(@RequestBody AppointmentDto appointment) {
        try {
            AppointmentDto appointmentUpdated = appointmentServicePort.update(appointment);
            return new ResponseEntity<>(appointmentServicePort.getById(appointmentUpdated.getAppointmentId()), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el agendamiento en la base de datos");
        } catch (DataIntegrityViolationException e) {
            dataIntegrityViolationExceptionHandler(e);
            return null;
        }
    }

    private void dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) throws ResponseStatusException {
        if (e.toString().contains("PropertyValueException")) {
            if (e.toString().contains("planId")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Plan no ingresado");
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Restricción violada: " + e.getMessage());
            }
        } else if (e.toString().contains("FK_APPOINTMENT_PLAN")) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Referencia a planId no existente");
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Restricción violada: " + e.getMessage());
        }
    }
}
