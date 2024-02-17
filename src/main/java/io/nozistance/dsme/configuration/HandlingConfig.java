package io.nozistance.dsme.configuration;

import io.nozistance.dsme.service.WebhookService;
import io.nozistance.dsme.telegram.UpdateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class HandlingConfig {

    @Bean
    public WebhookService webhookService(AbsSender sender,
                                         List<UpdateHandler> handlers,
                                         UpdateHandler defaultHandler) {
        var ordered = new ArrayList<>(handlers);
        ordered.remove(defaultHandler);
        ordered.add(defaultHandler);
        return new WebhookService(ordered, sender);
    }
}
