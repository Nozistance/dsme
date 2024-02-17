package io.nozistance.dsme.telegram;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface UpdateHandler {

    void handle(Update update, AbsSender sender);
    boolean supports(Update update);
}
