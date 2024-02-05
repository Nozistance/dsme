package io.nozistance.dsme.bot;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

@Configuration
@AllArgsConstructor
class BotConfig {

    private final BotProperties botProperties;

    @Bean
    public SetWebhook setWebhook() {
        return SetWebhook.builder()
                .url(botProperties.getPath())
                .build();
    }
}
