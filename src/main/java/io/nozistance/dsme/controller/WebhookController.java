package io.nozistance.dsme.controller;

import io.nozistance.dsme.event.UpdateReceivedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
@RequiredArgsConstructor
@RequestMapping("/callback")
public class WebhookController {

    private final ApplicationEventPublisher publisher;

    @PostMapping
    public void onWebhookUpdateReceived(@RequestBody Update update) {
        publisher.publishEvent(new UpdateReceivedEvent(this, update));
    }
}
