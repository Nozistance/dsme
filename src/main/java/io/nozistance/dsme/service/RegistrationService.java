package io.nozistance.dsme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RestTemplate restTemplate;
    private final HttpEntity<?> requestEntity;
    private final String serverUri;

    @EventListener(ApplicationReadyEvent.class)
    public void register() {
        restTemplate.postForEntity(
                serverUri,
                requestEntity,
                String.class
        );
    }
}
