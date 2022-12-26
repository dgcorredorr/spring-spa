package com.techcamp.spa.application.configuration;

import com.techcamp.spa.domain.ports.api.InvoiceServicePort;
import com.techcamp.spa.domain.ports.spi.InvoicePersistencePort;
import com.techcamp.spa.domain.service.InvoiceServiceImpl;
import com.techcamp.spa.infrastructure.adapter.InvoicePersistenceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReportConfig {

    @Bean
    public InvoicePersistencePort invoicePersistence() {
        return new InvoicePersistenceAdapter();
    }

    @Bean
    public InvoiceServicePort invoiceService() {
        return new InvoiceServiceImpl(invoicePersistence());
    }

}
