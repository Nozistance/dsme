package io.nozistance.dsme.handler;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {

    boolean supports(Update update);
    PartialBotApiMethod<?> handle(Update update);
}
