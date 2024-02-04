package io.nozistance.dsme.controller;

import io.nozistance.dsme.service.handler.UpdateHandler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@AllArgsConstructor
@RequestMapping("/callback/update")
public class MenuBotController {

    private final UpdateHandler updateHandler;

    @PostMapping
    public PartialBotApiMethod<?> onWebhookUpdateReceived(@RequestBody Update update) {
        return updateHandler.handle(update);
    }
}
