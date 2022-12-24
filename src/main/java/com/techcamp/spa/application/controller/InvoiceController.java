package com.techcamp.spa.application.controller;

import com.techcamp.spa.domain.data.InvoiceDto;
import com.techcamp.spa.domain.enums.ReportType;
import com.techcamp.spa.domain.ports.api.InvoiceServicePort;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = "${controller.cross-origins-path}")
public class InvoiceController {

    private final InvoiceServicePort invoiceServicePort;

    @Autowired
    public InvoiceController(InvoiceServicePort invoiceServicePort) {
        this.invoiceServicePort = invoiceServicePort;
    }

    @GetMapping("/invoice")
    public ResponseEntity<Resource> downloadInvoice(@RequestParam Map<String, Object> params)
            throws JRException, IOException, SQLException {
        InvoiceDto dto = invoiceServicePort.getInvoice(params);

        InputStreamResource streamResource = new InputStreamResource(dto.getStream());
        MediaType mediaType;
        if (params.get("reportType").toString().equalsIgnoreCase(ReportType.EXCEL.name())) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        } else {
            mediaType = MediaType.APPLICATION_PDF;
        }

        return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
                .contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
    }
}
