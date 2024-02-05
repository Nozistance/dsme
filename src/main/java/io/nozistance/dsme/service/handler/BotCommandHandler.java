package io.nozistance.dsme.service.handler;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class BotCommandHandler implements Handler<Message> {

    @Override
    public PartialBotApiMethod<?> handle(Message botCommand) {
        return switch (botCommand.getText()) {
            case "/day" -> day(botCommand);
            case "/menu" -> menu(botCommand);
            case "/subscribe" -> subscribe(botCommand);
            case "/unsubscribe" -> unsubscribe(botCommand);
            default -> null;
        };
    }

    private PartialBotApiMethod<?> day(Message botCommand) {
        return null;
    }

    private PartialBotApiMethod<?> menu(Message botCommand) {
        return null;
    }

    private PartialBotApiMethod<?> subscribe(Message botCommand) {
        return null;
    }

    private PartialBotApiMethod<?> unsubscribe(Message botCommand) {
        return null;
    }
}
