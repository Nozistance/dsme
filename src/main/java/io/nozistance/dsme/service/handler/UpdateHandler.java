package io.nozistance.dsme.service.handler;

import io.nozistance.dsme.service.Handler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@AllArgsConstructor
public class UpdateHandler implements Handler<Update> {

    private final MessageHandler messageHandler;
    private final CallQueryHandler callQueryHandler;
    private final BotCommandHandler botCommandHandler;

    @Override
    public PartialBotApiMethod<?> handle(Update update) {
        log.debug(update.toString());
        if (update.hasMessage() && update.getMessage().hasText()) {
            return update.getMessage().isCommand()
                    ? botCommandHandler.handle(update.getMessage())
                    : messageHandler.handle(update.getMessage());
        } else if (update.hasCallbackQuery()) {
            return callQueryHandler.handle(update.getCallbackQuery());
        } else return null;
    }
}
