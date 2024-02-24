package io.nozistance.dsme.configuration;

import io.nozistance.dsme.properties.WebhookProperties;
import io.nozistance.dsme.telegram.Webhook;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
public class WebhookConfig {

    @Bean
    public SetWebhook setWebhook(WebhookProperties properties) {
        return SetWebhook.builder()
                .secretToken(properties.getSecretToken())
                .url(properties.getUrl())
                .build();
    }

    @Bean
    public Webhook webhook(WebhookProperties properties) {
        return new Webhook("", properties.getToken(), "");
    }

    @Bean
    public ApplicationRunner setWebhookApplicationRunner(Webhook webhook, SetWebhook setWebhook) {
        return args -> webhook.setWebhook(setWebhook);
    }
}
