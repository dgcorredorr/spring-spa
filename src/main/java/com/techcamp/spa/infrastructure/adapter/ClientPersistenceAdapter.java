package com.techcamp.spa.infrastructure.adapter;

import com.techcamp.spa.domain.data.ClientDto;
import com.techcamp.spa.domain.ports.spi.ClientPersistencePort;
import com.techcamp.spa.infrastructure.mapper.ClientMapper;
import com.techcamp.spa.infrastructure.repository.ClientJdbcRepository;
import com.techcamp.spa.infrastructure.repository.ClientJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ClientPersistenceAdapter implements ClientPersistencePort {

    @Autowired
    private ClientJpaRepository clientJpaRepository;

    @Autowired
    private ClientJdbcRepository clientJdbcRepository;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDto save(ClientDto client) throws DataIntegrityViolationException {
        return clientMapper.toDomain(clientJpaRepository.save(clientMapper.toEntity(client)));
    }

    @Override
    public ClientDto getById(Long id) throws NoSuchElementException {
        return clientMapper.toDomain(clientJpaRepository.findById(id).orElseThrow());
    }

    @Override
    public Page<ClientDto> getAll(Pageable pageable) throws NoSuchElementException {
        Page<ClientDto> clientPage = clientMapper.toDomainPage(clientJpaRepository.findAll(pageable));
        if (clientPage.isEmpty()) {
            throw new NoSuchElementException();
        }
        return clientPage;
    }

    @Override
    public Page<ClientDto> getByNameContainingAndDocumentNumberContaining(String name, String documentNumber, Pageable pageable) throws NoSuchElementException, InvalidDataAccessResourceUsageException {
        Page<ClientDto> clientPage = clientMapper.toDomainPage(clientJpaRepository.getByDocumentNumberContainingIgnoreCaseOrNameContainingIgnoreCase(name, documentNumber, pageable));
        if (clientPage.isEmpty()) {
            throw new NoSuchElementException();
        }
        return clientPage;
    }

    @Override
    public ClientDto update(ClientDto clientDto) throws NoSuchElementException {
        if (clientDto.getClientId() == null) {
            throw new DataIntegrityViolationException("ClientId no ingresado");
        } else if (clientJpaRepository.existsById(clientDto.getClientId())) {
            return save(clientDto);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(Long clientId) throws NoSuchElementException {
        if (clientJpaRepository.existsById(clientId)) {
            clientJpaRepository.deleteById(clientId);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void delete(ClientDto clientDto) throws NoSuchElementException {
        if (clientDto.getClientId() == null) {
            throw new DataIntegrityViolationException("ClientId no ingresado");
        } else if (clientJpaRepository.existsById(clientDto.getClientId())) {
            clientJpaRepository.delete(clientMapper.toEntity(clientDto));
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteInactiveClients() {
        clientJdbcRepository.deleteInactiveClients();
    }
}
