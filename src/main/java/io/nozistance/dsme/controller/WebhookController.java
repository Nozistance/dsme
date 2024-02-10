package io.nozistance.dsme.controller;

import io.nozistance.dsme.service.WebhookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
public class WebhookController {

    private final WebhookService webhookService;

    @PostMapping
    public PartialBotApiMethod<?> onWebhookUpdateReceived(@RequestBody Update update) {
        return webhookService.handle(update);
    }
}
