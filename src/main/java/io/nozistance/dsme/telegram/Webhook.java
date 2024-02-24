package io.nozistance.dsme.telegram;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
@ToString
@RequiredArgsConstructor
public class Webhook extends TelegramWebhookBot {

    private final String botUsername;
    private final String botToken;
    private final String botPath;

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }
}
