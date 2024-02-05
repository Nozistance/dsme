package io.nozistance.dsme.service.handler;

import io.nozistance.dsme.configuration.TextProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

@Service
@AllArgsConstructor
public class BotCommandHandler implements Handler<Message> {

    private final List<KeyboardRow> dayOfWeekButtons;
    private final TextProperties text;

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
        return SendMessage.builder()
                .replyMarkup(new ReplyKeyboardMarkup(dayOfWeekButtons))
                .chatId(botCommand.getChatId())
                .text(text.getDayKeyboard())
                .build();
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