package com.techcamp.spa.application.controller;

import com.techcamp.spa.domain.data.SessionInfoDto;
import com.techcamp.spa.domain.ports.api.SessionInfoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/session_info")
public class SessionInfoController {

    private final SessionInfoServicePort sessionInfoServicePort;

    @Autowired
    public SessionInfoController(SessionInfoServicePort sessionInfoServicePort) {
        this.sessionInfoServicePort = sessionInfoServicePort;
    }

    @GetMapping("/check")
    public ResponseEntity<?> isAvailable(@RequestParam Long clientId,
                                         @RequestParam Short specialistId,
                                         @RequestParam Long appointmentId,
                                         @RequestParam String sessionDate) {
        try {
            HashMap<String, Object> responseMap = new HashMap<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            responseMap.put("isAvailable", sessionInfoServicePort.isAvailable(clientId, specialistId, appointmentId, LocalDateTime.parse(sessionDate, formatter))==1);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (UncategorizedSQLException e) {
            String msg = e.getSQLException().getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msg.substring(msg.indexOf(":")+2, msg.indexOf("\nORA")));
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de fecha incorrecto. Formato correcto: yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<SessionInfoDto>> read(Pageable pageable) {
        try {
            return new ResponseEntity<>(sessionInfoServicePort.getAll(pageable), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron sesiones en la base de datos");
        }
    }
}
