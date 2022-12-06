package com.techcamp.spa.domain.service;

import com.techcamp.spa.domain.data.ClientDto;
import com.techcamp.spa.domain.ports.api.ClientServicePort;
import com.techcamp.spa.domain.ports.spi.ClientPersistencePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ClientServiceImpl implements ClientServicePort {

    private final ClientPersistencePort clientPersistencePort;

    public ClientServiceImpl(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }

    @Override
    public ClientDto save(ClientDto client) {
        return clientPersistencePort.save(client);
    }

    @Override
    public ClientDto getById(Long id) {
        return clientPersistencePort.getById(id);
    }

    @Override
    public Page<ClientDto> getAll(Pageable pageable) {
        return clientPersistencePort.getAll(pageable);
    }

    @Override
    public Page<ClientDto> getByNameContainingAndDocumentNumberContaining(String name, String documentNumber, Pageable pageable) {
        return clientPersistencePort.getByNameContainingAndDocumentNumberContaining(name, documentNumber, pageable);
    }

    @Override
    public ClientDto update(ClientDto client) {
        return clientPersistencePort.update(client);
    }

    @Override
    public void deleteById(Long clientId) {
        clientPersistencePort.deleteById(clientId);
    }

    @Override
    public void delete(ClientDto client) {
        clientPersistencePort.delete(client);
    }
}
