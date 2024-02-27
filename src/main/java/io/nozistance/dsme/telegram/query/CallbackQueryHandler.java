package io.nozistance.dsme.telegram.query;

import io.nozistance.dsme.telegram.UpdateHandler;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface CallbackQueryHandler extends UpdateHandler {

    @Override
    default boolean supports(Update update) {
        return update.hasCallbackQuery()
                && update.getCallbackQuery().getData()
                .startsWith(getQuery() + ":");
    }

    @SneakyThrows
    default void handle(Update update, AbsSender sender) {
        answer(update, sender, args(update));
        String id = update.getCallbackQuery().getId();
        sender.execute(new AnswerCallbackQuery(id));
    }

    private String args(Update update) {
        return update.getCallbackQuery()
                .getData().split(":")[1];
    }

    void answer(Update update, AbsSender absSender, String args);

    String getQuery();
}
