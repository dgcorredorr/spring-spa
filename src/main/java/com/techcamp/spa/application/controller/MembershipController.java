package com.techcamp.spa.application.controller;

import com.techcamp.spa.domain.data.MembershipDto;
import com.techcamp.spa.domain.ports.api.MembershipServicePort;
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
@RequestMapping("/membership")
@CrossOrigin(origins = "${controller.cross-origins-path}")
public class MembershipController {

    private final MembershipServicePort membershipServicePort;

    @Autowired
    public MembershipController(MembershipServicePort membershipServicePort) {
        this.membershipServicePort = membershipServicePort;
    }

    @GetMapping
    public ResponseEntity<List<MembershipDto>> read() {
        try {
            return new ResponseEntity<>(membershipServicePort.getAll(), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron membresías en la base de datos");
        }
    }
}
