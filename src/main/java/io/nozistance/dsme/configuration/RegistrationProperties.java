package io.nozistance.dsme.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties("registration")
public class RegistrationProperties {

    private String domain;
    private String uriFormat;
    private String token;
}
