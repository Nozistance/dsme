package io.nozistance.dsme.configuration;

import io.nozistance.dsme.properties.RegistrationProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class RegistrationConfig {

    private final RegistrationProperties properties;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public String serverUri() {
        return properties.getUriFormat()
                .formatted(properties.getToken());
    }

    @Bean
    public HttpEntity<?> webhookEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        var body = new LinkedMultiValueMap<String, String>();
        body.add("url", "https://%s".formatted(
                properties.getDomain()
        ));
        return new HttpEntity<>(body, headers);
    }
}
