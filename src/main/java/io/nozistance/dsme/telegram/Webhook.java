package io.nozistance.dsme.telegram;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Getter
@ToString
@RequiredArgsConstructor
public class Webhook extends TelegramWebhookBot {

    private final SetWebhook setWebhook;
    private final String botUsername;
    private final String botToken;
    private final String botPath;

    @SneakyThrows
    @PostConstruct
    public void init() {
        setWebhook(setWebhook);
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }
}