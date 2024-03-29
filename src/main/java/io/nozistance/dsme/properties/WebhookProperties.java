package io.nozistance.dsme.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@NotBlank
@RequiredArgsConstructor
@ConfigurationProperties("webhook.registration")
public class WebhookProperties {

    private final String secretToken;
    private final String token;
    private final String url;
}
