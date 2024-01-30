package io.nozistance.dsme.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties
public class ApplicationProperties {

    private final String uriFormat;
}
