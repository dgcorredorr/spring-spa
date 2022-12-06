package com.techcamp.spa.configuration;

import com.techcamp.spa.domain.ports.api.*;
import com.techcamp.spa.domain.ports.spi.*;
import com.techcamp.spa.domain.service.*;
import com.techcamp.spa.infrastructure.adapter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrudConfig {
    @Bean
    public ClientPersistencePort clientPersistence() {
        return new ClientJpaAdapter();
    }

    @Bean
    public ClientServicePort clientService() {
        return new ClientServiceImpl(clientPersistence());
    }

    @Bean
    public DocumentTypePersistencePort documentTypePersistence() {
        return new DocumentTypeJpaAdapter();
    }

    @Bean
    public DocumentTypeServicePort documentTypeService() {
        return new DocumentTypeServiceImpl(documentTypePersistence());
    }

    @Bean
    public GenderPersistencePort genderPersistence() {
        return new GenderJpaAdapter();
    }

    @Bean
    public GenderServicePort genderService() {
        return new GenderServiceImpl(genderPersistence());
    }

    @Bean
    public MembershipPersistencePort membershipPersistence() {
        return new MembershipJpaAdapter();
    }

    @Bean
    public MembershipServicePort membershipService() {
        return new MembershipServiceImpl(membershipPersistence());
    }

    @Bean
    public AreaPersistencePort areaPersistence() {
        return new AreaJpaAdapter();
    }

    @Bean
    public AreaServicePort areaService() {
        return new AreaServiceImpl(areaPersistence());
    }

    @Bean
    public WorkHoursPersistencePort workHoursPersistence() {
        return new WorkHoursJpaAdapter();
    }

    @Bean
    public WorkHoursServicePort workHoursService() {
        return new WorkHoursServiceImpl(workHoursPersistence());
    }

    @Bean
    public SpecialistPersistencePort specialistPersistence() {
        return new SpecialistJpaAdapter();
    }

    @Bean
    public SpecialistServicePort specialistService() {
        return new SpecialistServiceImpl(specialistPersistence());
    }

}
