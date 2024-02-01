package io.nozistance.dsme.controller;

import io.nozistance.dsme.bot.MenuBot;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@AllArgsConstructor
@RequestMapping("/callback/update")
public class MenuBotController {

    private final MenuBot menuBot;

    @PostMapping
    public BotApiMethod<?> onWebhookUpdateReceived(@RequestBody Update update) {
        return menuBot.onWebhookUpdateReceived(update);
    }
}
