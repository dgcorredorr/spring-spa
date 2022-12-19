package com.techcamp.spa.domain.ports.spi;

import com.techcamp.spa.domain.data.InvoiceDto;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface InvoicePersistencePort {
    InvoiceDto getInvoice(Map<String, Object> params) throws JRException, IOException, SQLException;
}
