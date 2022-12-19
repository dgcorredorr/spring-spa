package com.techcamp.spa.domain.service;

import com.techcamp.spa.domain.data.InvoiceDto;
import com.techcamp.spa.domain.ports.api.InvoiceServicePort;
import com.techcamp.spa.domain.ports.spi.InvoicePersistencePort;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class InvoiceServiceImpl implements InvoiceServicePort {

    private final InvoicePersistencePort invoicePersistencePort;

    public InvoiceServiceImpl(InvoicePersistencePort invoicePersistencePort){
        this.invoicePersistencePort = invoicePersistencePort;
    }
    @Override
    public InvoiceDto getInvoice(Map<String, Object> params) throws JRException, IOException, SQLException {
        return invoicePersistencePort.getInvoice(params);
    }
}
