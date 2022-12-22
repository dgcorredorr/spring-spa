package com.techcamp.spa.application.controller;

import com.techcamp.spa.domain.data.AreaDto;
import com.techcamp.spa.domain.ports.api.AreaServicePort;
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
@RequestMapping("/area")
@CrossOrigin(origins = "http://localhost:4200")
public class AreaController {

    private final AreaServicePort areaServicePort;

    @Autowired
    public AreaController(AreaServicePort areaServicePort) {
        this.areaServicePort = areaServicePort;
    }

    @GetMapping
    public ResponseEntity<List<AreaDto>> read() {
        try {
            return new ResponseEntity<>(areaServicePort.getAll(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron especialidades en la base de datos");
        }
    }
}
