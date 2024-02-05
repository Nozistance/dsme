package io.nozistance.dsme.bot;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.starter.SpringWebhookBot;

@Slf4j
@Component
class MenuBot extends SpringWebhookBot {

    private final BotProperties botProperties;
    private final SetWebhook setWebhook;

    public MenuBot(SetWebhook setWebhook, BotProperties botProperties) {
        super(setWebhook, botProperties.getToken());
        this.botProperties = botProperties;
        this.setWebhook = setWebhook;
    }

    @PostConstruct
    public void init() throws TelegramApiException {
        setWebhook(setWebhook);
    }

    @Override
    public String getBotPath() {
        return "/update";
    }

    @Override
    public String getBotUsername() {
        return botProperties.getName();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }
}
