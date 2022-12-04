package com.techcamp.spa.domain.ports.api;

import com.techcamp.spa.domain.data.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientServicePort {

    ClientDto save(ClientDto client);

    ClientDto getById(Long id);

    Page<ClientDto> getAll(Pageable pageable);

    Page<ClientDto> getByDocumentNumberContaining(String documentNumber, Pageable pageable);

    Page<ClientDto> getByNameContaining(String name, Pageable pageable);

    ClientDto update(ClientDto client);

    void deleteById(Long clientId);

    void delete(ClientDto client);

}
