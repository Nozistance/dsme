package io.nozistance.dsme.handler.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartCommandHandler implements CommandHandler {

    @Override
    public PartialBotApiMethod<?> handle(Update update) {
        return new SendMessage(update.getMessage().getChatId().toString(), "hello");
    }

    @Override
    public String getCommand() {
        return "/start";
    }
}
