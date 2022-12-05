package com.techcamp.spa.configuration;

import com.techcamp.spa.domain.ports.api.GenderServicePort;
import com.techcamp.spa.domain.ports.spi.GenderPersistencePort;
import com.techcamp.spa.domain.service.GenderServiceImpl;
import com.techcamp.spa.infrastructure.adapter.GenderJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenderConfig {
    @Bean
    public GenderPersistencePort genderPersistence() {
        return new GenderJpaAdapter();
    }

    @Bean
    public GenderServicePort genderService() {
        return new GenderServiceImpl(genderPersistence());
    }
}
