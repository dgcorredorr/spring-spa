package com.techcamp.spa.domain.ports.spi;

import com.techcamp.spa.domain.data.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientPersistencePort {

    ClientDto save(ClientDto client);

    ClientDto getById(Long id);

    Page<ClientDto> getAll(Pageable pageable);

    Page<ClientDto> getByNameContainingAndDocumentNumberContaining(String name, String documentNumber, Pageable pageable);

    ClientDto update(ClientDto client);

    void deleteById(Long clientId);

    void delete(ClientDto client);

    void deleteInactiveClients();

}
