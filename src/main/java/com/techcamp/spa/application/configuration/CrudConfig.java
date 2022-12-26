package com.techcamp.spa.application.configuration;

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
        return new ClientPersistenceAdapter();
    }

    @Bean
    public ClientServicePort clientService() {
        return new ClientServiceImpl(clientPersistence());
    }

    @Bean
    public DocumentTypePersistencePort documentTypePersistence() {
        return new DocumentTypePersistenceAdapter();
    }

    @Bean
    public DocumentTypeServicePort documentTypeService() {
        return new DocumentTypeServiceImpl(documentTypePersistence());
    }

    @Bean
    public GenderPersistencePort genderPersistence() {
        return new GenderPersistenceAdapter();
    }

    @Bean
    public GenderServicePort genderService() {
        return new GenderServiceImpl(genderPersistence());
    }

    @Bean
    public MembershipPersistencePort membershipPersistence() {
        return new MembershipPersistenceAdapter();
    }

    @Bean
    public MembershipServicePort membershipService() {
        return new MembershipServiceImpl(membershipPersistence());
    }

    @Bean
    public AreaPersistencePort areaPersistence() {
        return new AreaPersistenceAdapter();
    }

    @Bean
    public AreaServicePort areaService() {
        return new AreaServiceImpl(areaPersistence());
    }

    @Bean
    public WorkHoursPersistencePort workHoursPersistence() {
        return new WorkHoursPersistenceAdapter();
    }

    @Bean
    public WorkHoursServicePort workHoursService() {
        return new WorkHoursServiceImpl(workHoursPersistence());
    }

    @Bean
    public SpecialistPersistencePort specialistPersistence() {
        return new SpecialistPersistenceAdapter();
    }

    @Bean
    public SpecialistServicePort specialistService() {
        return new SpecialistServiceImpl(specialistPersistence());
    }

    @Bean
    public PlanPersistencePort planPersistence() {
        return new PlanPersistenceAdapter();
    }

    @Bean
    public PlanServicePort planService() {
        return new PlanServiceImpl(planPersistence());
    }

    @Bean
    public AppointmentPersistencePort appointmentPersistence() {
        return new AppointmentPersistenceAdapter();
    }

    @Bean
    public AppointmentServicePort appointmentService() {
        return new AppointmentServiceImpl(appointmentPersistence(), planPersistence());
    }

    @Bean
    public SessionInfoPersistencePort sessionInfoPersistence() {
        return new SessionInfoPersistenceAdapter();
    }

    @Bean
    public SessionInfoServicePort sessionInfoService() {
        return new SessionInfoServiceImpl(sessionInfoPersistence());
    }


}
