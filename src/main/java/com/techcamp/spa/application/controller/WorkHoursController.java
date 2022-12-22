package com.techcamp.spa.application.controller;

import com.techcamp.spa.domain.data.WorkHoursDto;
import com.techcamp.spa.domain.ports.api.WorkHoursServicePort;
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
@RequestMapping("/work_hours")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkHoursController {

    private final WorkHoursServicePort workHoursServicePort;

    @Autowired
    public WorkHoursController(WorkHoursServicePort workHoursServicePort) {
        this.workHoursServicePort = workHoursServicePort;
    }

    @GetMapping
    public ResponseEntity<List<WorkHoursDto>> read() {
        try {
            return new ResponseEntity<>(workHoursServicePort.getAll(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron horas laborales en la base de datos");
        }
    }
}
