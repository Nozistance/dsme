package io.nozistance.dsme.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.Optional;

public class UpdateAnswer extends SendMessage {

    public UpdateAnswer(Update update, String text) {
        this(update, text, null);
    }

    public UpdateAnswer(Update update, String text, ReplyKeyboard keyboard) {
        setReplyMarkup(keyboard);
        setChatId(update);
        setText(text);
    }

    private void setChatId(Update update) {
        Optional.ofNullable(update.getMessage())
                .or(() -> Optional
                        .ofNullable(update.getCallbackQuery())
                        .map(CallbackQuery::getMessage))
                .map(Message::getChatId)
                .ifPresent(this::setChatId);
    }
}
