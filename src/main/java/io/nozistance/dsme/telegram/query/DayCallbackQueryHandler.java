package io.nozistance.dsme.telegram.query;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class DayCallbackQueryHandler implements CallbackQueryHandler {

    @Override
    @SneakyThrows(TelegramApiException.class)
    public void handle(Update update, AbsSender sender) {
        sender.execute(new AnswerCallbackQuery(update.getCallbackQuery().getId()));
    }

    @Override
    public String getQuery() {
        return "day";
    }
}
