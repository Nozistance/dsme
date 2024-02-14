package io.nozistance.dsme.service;

import io.nozistance.dsme.handler.UpdateHandler;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class WebhookService {

    private final List<UpdateHandler> handlers;

    public Optional<PartialBotApiMethod<?>> handle(Update update) {
        return handlers.stream()
                .filter(h -> h.supports(update))
                .findFirst().map(h -> h.handle(update));
    }
}
