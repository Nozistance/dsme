package io.nozistance.dsme.telegram;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CallbackQueryAnswer extends AnswerCallbackQuery {

    public CallbackQueryAnswer(Update update) {
        this(update, null);
    }

    public CallbackQueryAnswer(Update update, String text) {
        setCallbackQueryId(update.getCallbackQuery().getId());
        setText(text);
    }
}
