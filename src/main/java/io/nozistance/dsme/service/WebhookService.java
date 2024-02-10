package io.nozistance.dsme.service;

import io.nozistance.dsme.handler.UpdateHandler;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Service
public class WebhookService {

    private final List<UpdateHandler> handlers;
    private final UpdateHandler defaultHandler;

    public WebhookService(List<UpdateHandler> handlers, UpdateHandler defaultHandler) {
        this.defaultHandler = defaultHandler;
        handlers.remove(defaultHandler);
        this.handlers = handlers;
    }

    public PartialBotApiMethod<?> handle(Update update) {
        return handlers.stream()
                .filter(h -> h.supports(update))
                .findFirst().orElse(defaultHandler)
                .handle(update);
    }
}
