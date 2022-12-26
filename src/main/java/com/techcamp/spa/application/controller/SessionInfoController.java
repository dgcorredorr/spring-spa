package com.techcamp.spa.application.controller;

import com.techcamp.spa.domain.data.SessionInfoDto;
import com.techcamp.spa.domain.ports.api.SessionInfoServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/session_info")
@CrossOrigin(origins = "${controller.cross-origins-path}")
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

    @GetMapping("/specialist/{specialistId}")
    public ResponseEntity<Page<SessionInfoDto>> readBySpecialist(@PathVariable("specialistId") Short specialistId, Pageable pageable) {
        try {
            return new ResponseEntity<>(sessionInfoServicePort.getBySpecialistId(specialistId, pageable), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron sesiones en la base de datos");
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Page<SessionInfoDto>> readByClient(@PathVariable("clientId") Long clientId, Pageable pageable) {
        try {
            return new ResponseEntity<>(sessionInfoServicePort.getByClientId(clientId, pageable), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron sesiones en la base de datos");
        }
    }

    @PostMapping
    public ResponseEntity<SessionInfoDto> create(@RequestBody SessionInfoDto sessionInfoDto) {
        try {
            SessionInfoDto sessionSaved = sessionInfoServicePort.save(sessionInfoDto);
            return new ResponseEntity<>(sessionInfoServicePort.getById(sessionSaved.getSessionInfoId()), HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se guardó el cliente en la base de datos");
        } catch (DataIntegrityViolationException e) {
            dataIntegrityViolationExceptionHandler(e);
            return null;
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agenda no disponible para los datos ingresados");
        } catch (UncategorizedSQLException e) {
            String msg = e.getSQLException().getMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, msg.substring(msg.indexOf(":")+2, msg.indexOf("\nORA")));
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de fecha incorrecto. Formato correcto: yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private void dataIntegrityViolationExceptionHandler(DataIntegrityViolationException e) throws ResponseStatusException {
        if (e.toString().contains("PropertyValueException")) {
            if (e.toString().contains("clientId")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Cliente no ingresado");
            } else if (e.toString().contains("specialistId")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Especialista no ingresado");
            } else if (e.toString().contains("appointmentId")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Agendamiento no ingresado");
            } else if (e.toString().contains("sessionDate")) {
                throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Fecha y hora no ingresadas");
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Restricción violada: " + e.getMessage());
            }
        } else if (e.toString().contains("FK_SESSION_INFO_CLIENT")) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Referencia a clientId no existente");
        } else if (e.toString().contains("FK_SESSION_INFO_SPECIALIST")) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Referencia a specialistId no existente");
        } else if (e.toString().contains("FK_SESSION_INFO_APPOINTMENT")) {
            throw new ResponseStatusException(HttpStatus.FAILED_DEPENDENCY, "Referencia a appointmentId no existente");
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Restricción violada: " + e.getMessage());
        }
    }
}
