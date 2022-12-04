package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.ClientDto;
import com.techcamp.spa.infrastructure.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper {
    private final GenderMapper genderMapper;
    private final DocumentTypeMapper documentTypeMapper;
    private final MembershipMapper membershipMapper;

    @Autowired
    public ClientMapper(GenderMapper genderMapper, DocumentTypeMapper documentTypeMapper, MembershipMapper membershipMapper) {
        this.genderMapper = genderMapper;
        this.documentTypeMapper = documentTypeMapper;
        this.membershipMapper = membershipMapper;
    }
    public ClientDto toDomain(Client client) {
        return ClientDto.builder()
                .clientId(client.getClientId())
                .genderId(client.getGenderId())
                .documentTypeId(client.getDocumentTypeId())
                .membershipId(client.getMembershipId())
                .firstName(client.getFirstName())
                .middleName(client.getMiddleName())
                .lastName(client.getLastName())
                .documentNumber(client.getDocumentNumber())
                .birthDate(client.getBirthDate())
                .gender(genderMapper.toDomain(client.getGender()))
                .documentType(documentTypeMapper.toDomain(client.getDocumentType()))
                .membership(membershipMapper.toDomain(client.getMembership()))
                .build();
    }

    public Client toEntity(ClientDto client) {
        return Client.builder()
                .clientId(client.getClientId())
                .genderId(client.getGenderId())
                .documentTypeId(client.getDocumentTypeId())
                .membershipId(client.getMembershipId())
                .firstName(client.getFirstName())
                .middleName(client.getMiddleName())
                .lastName(client.getLastName())
                .documentNumber(client.getDocumentNumber())
                .birthDate(client.getBirthDate())
                .gender(genderMapper.toEntity(client.getGender()))
                .documentType(documentTypeMapper.toEntity(client.getDocumentType()))
                .membership(membershipMapper.toEntity(client.getMembership()))
                .build();
    }

    public List<ClientDto> toDomainList(List<Client> clientList) {
        return clientList.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<Client> toEntityList(List<ClientDto> clientList) {
        return clientList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public Page<ClientDto> toDomainPage(Page<Client> clientPage) {
        return clientPage.map(this::toDomain);
    }

}
