package com.techcamp.spa.infrastructure.adapter;

import com.techcamp.spa.domain.commons.JasperReportManager;
import com.techcamp.spa.domain.data.InvoiceDto;
import com.techcamp.spa.domain.enums.ReportType;
import com.techcamp.spa.domain.ports.spi.InvoicePersistencePort;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@Service
public class InvoicePersistenceAdapter implements InvoicePersistencePort {
    @Autowired
    private JasperReportManager reportManager;

    @Autowired
    private DataSource dataSource;

    @Override
    public InvoiceDto getInvoice(Map<String, Object> params) throws JRException, IOException, SQLException {
        String fileName = "customer_invoice";
        InvoiceDto invoiceDto = new InvoiceDto();
        String extension = params.get("reportType").toString().equalsIgnoreCase(ReportType.EXCEL.name()) ? ".xlsx"
                : ".pdf";
        invoiceDto.setFileName(fileName + extension);

        ByteArrayOutputStream stream = reportManager.export(fileName, params.get("reportType").toString(), params,
                dataSource.getConnection());

        byte[] bs = stream.toByteArray();
        invoiceDto.setStream(new ByteArrayInputStream(bs));
        invoiceDto.setLength(bs.length);

        return invoiceDto;
    }
}
