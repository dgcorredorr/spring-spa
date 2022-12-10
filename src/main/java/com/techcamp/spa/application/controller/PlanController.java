package com.techcamp.spa.application.controller;

import com.techcamp.spa.domain.data.PlanDto;
import com.techcamp.spa.domain.ports.api.PlanServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/plan")
public class PlanController {

    private final PlanServicePort planServicePort;

    @Autowired
    public PlanController(PlanServicePort planServicePort) {
        this.planServicePort = planServicePort;
    }

    @GetMapping
    public ResponseEntity<List<PlanDto>> read() {
        try {
            return new ResponseEntity<>(planServicePort.getAll(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron planes en la base de datos");
        }
    }
}

