package com.techcamp.spa.application.controller;

import com.techcamp.spa.domain.data.GenderDto;
import com.techcamp.spa.domain.ports.api.GenderServicePort;
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
@RequestMapping("/gender")
public class GenderController {

    private final GenderServicePort genderServicePort;

    @Autowired
    public GenderController(GenderServicePort genderServicePort) {
        this.genderServicePort = genderServicePort;
    }

    @GetMapping
    public ResponseEntity<List<GenderDto>> read() {
        try {
            return new ResponseEntity<>(genderServicePort.getAll(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron géneros en la base de datos");
        }
    }
}
