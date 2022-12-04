package com.techcamp.spa.configuration;

import com.techcamp.spa.domain.ports.api.ClientServicePort;
import com.techcamp.spa.domain.ports.spi.ClientPersistencePort;
import com.techcamp.spa.domain.service.ClientServiceImpl;
import com.techcamp.spa.infrastructure.adapter.ClientJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public ClientPersistencePort clientPersistence() {
        return new ClientJpaAdapter();
    }

    @Bean
    public ClientServicePort clientService() {
        return new ClientServiceImpl(clientPersistence());
    }

}
