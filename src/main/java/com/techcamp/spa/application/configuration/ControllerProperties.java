package com.techcamp.spa.application.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "controller")
@Data
@Component
public class ControllerProperties {
    private String crossOriginsPath;
}
