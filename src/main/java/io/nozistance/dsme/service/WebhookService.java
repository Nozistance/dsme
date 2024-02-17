package io.nozistance.dsme.service;

import io.nozistance.dsme.event.UpdateReceivedEvent;
import io.nozistance.dsme.telegram.UpdateHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.util.List;

@RequiredArgsConstructor
public class WebhookService {

    private final List<UpdateHandler> handlers;
    private final AbsSender sender;

    @EventListener
    public void handle(UpdateReceivedEvent event) {
        Update update = event.getUpdate();
        handlers.stream()
                .filter(h -> h.supports(update)).findFirst()
                .ifPresent(h -> h.handle(update, sender));
    }
}
