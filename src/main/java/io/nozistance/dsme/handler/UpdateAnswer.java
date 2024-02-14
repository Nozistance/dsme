package io.nozistance.dsme.handler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public class UpdateAnswer extends SendMessage {

    public UpdateAnswer(Update update, String text) {
        super(update.getMessage().getChatId().toString(), text);
    }

    public UpdateAnswer(Update update, String text, ReplyKeyboard keyboard) {
        super(update.getMessage().getChatId().toString(), text);
        setReplyMarkup(keyboard);
    }
}
