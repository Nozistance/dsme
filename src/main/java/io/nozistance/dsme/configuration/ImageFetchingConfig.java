package io.nozistance.dsme.configuration;

import io.nozistance.dsme.properties.DataProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ImageFetchingConfig {

    private final DataProperties dataProperties;

    @Bean("imageFetching")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.rootUri(dataProperties.getUriFormat()).build();
    }
}
