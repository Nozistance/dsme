package io.nozistance.dsme.handler.command;

import io.nozistance.dsme.handler.UpdateHandler;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandHandler extends UpdateHandler {

    String getCommand();

    @Override
    default boolean supports(Update update) {
        return update.hasMessage()
                && update.getMessage().isCommand()
                && update.getMessage().getText()
                .equals(getCommand());
    }
}
