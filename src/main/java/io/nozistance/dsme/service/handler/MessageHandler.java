package io.nozistance.dsme.service.handler;

import io.nozistance.dsme.configuration.TextProperties;
import io.nozistance.dsme.service.MenuFormattingService;
import io.nozistance.dsme.util.DayOfWeek;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageHandler implements Handler<Message> {

    private final MenuFormattingService menuFormattingService;
    private final TextProperties text;

    @Override
    public PartialBotApiMethod<?> handle(Message message) {
        if (DayOfWeek.isDayOfWeek(message.getText()))
            return dailyMenu(message);
        else return null;
    }

    private PartialBotApiMethod<?> dailyMenu(Message message) {
        DayOfWeek day = DayOfWeek.from(message.getText());
        var menu = menuFormattingService.getMenu(day);
        var buttons = menu.stream()
                .map(p -> InlineKeyboardButton.builder()
                        .callbackData("datails:" + p.getFirst())
                        .text(p.getSecond()).build()).map(List::of)
                .toList();
        return SendMessage.builder()
                .replyToMessageId(message.getMessageId())
                .replyMarkup(new InlineKeyboardMarkup(buttons))
                .text(text.getSize().formatted(buttons.size()))
                .chatId(message.getChatId())
                .build();
    }
}
