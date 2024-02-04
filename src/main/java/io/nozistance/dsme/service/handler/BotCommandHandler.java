package io.nozistance.dsme.service.handler;

import io.nozistance.dsme.service.Handler;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class BotCommandHandler implements Handler<Message> {

    @Override
    public PartialBotApiMethod<?> handle(Message botCommand) {
        return null;
    }
}
