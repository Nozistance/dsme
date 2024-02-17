package io.nozistance.dsme.configuration;

import io.nozistance.dsme.properties.WebhookProperties;
import io.nozistance.dsme.telegram.Webhook;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@AllArgsConstructor
public class WebhookConfig {

    @Bean
    public SetWebhook setWebhook(WebhookProperties properties) {
        return SetWebhook.builder()
                .secretToken(properties.getSecretToken())
                .url(properties.getUrl())
                .build();
    }

    @Bean
    public Webhook telegramBot(SetWebhook setWebhook, WebhookProperties properties) {
        return new Webhook(setWebhook, "", properties.getToken(), "");
    }
}
