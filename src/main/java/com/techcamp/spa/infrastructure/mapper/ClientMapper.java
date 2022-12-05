package com.techcamp.spa.infrastructure.mapper;

import com.techcamp.spa.domain.data.ClientDto;
import com.techcamp.spa.infrastructure.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper extends Mapper<ClientDto, Client> {
    private final GenderMapper genderMapper;
    private final DocumentTypeMapper documentTypeMapper;
    private final MembershipMapper membershipMapper;

    @Autowired
    public ClientMapper(GenderMapper genderMapper, DocumentTypeMapper documentTypeMapper, MembershipMapper membershipMapper) {
        this.genderMapper = genderMapper;
        this.documentTypeMapper = documentTypeMapper;
        this.membershipMapper = membershipMapper;
    }
    @Override
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
    @Override
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

}
