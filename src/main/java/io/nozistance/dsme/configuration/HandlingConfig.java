package io.nozistance.dsme.configuration;

import io.nozistance.dsme.handler.UpdateHandler;
import io.nozistance.dsme.service.WebhookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class HandlingConfig {

    @Bean
    public WebhookService webhookService(List<UpdateHandler> handlers, UpdateHandler defaultHandler) {
        var ordered = new ArrayList<>(handlers);
        ordered.remove(defaultHandler);
        ordered.add(defaultHandler);
        return new WebhookService(ordered);
    }
}
