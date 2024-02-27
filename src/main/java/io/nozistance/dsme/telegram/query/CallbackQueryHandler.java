package io.nozistance.dsme.telegram.query;

import io.nozistance.dsme.telegram.UpdateHandler;
import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

public interface CallbackQueryHandler extends UpdateHandler {

    @Override
    default boolean supports(Update update) {
        return update.hasCallbackQuery()
                && update.getCallbackQuery().getData()
                .startsWith(getQuery() + ":");
    }

    String getQuery();

    @SneakyThrows
    default void handle(Update update, AbsSender sender) {
        answer(update, sender, args(update));
        String id = update.getCallbackQuery().getId();
        sender.execute(new AnswerCallbackQuery(id));
    }

    void answer(Update update, AbsSender absSender);

    default String args(Update update) {
        return update.getCallbackQuery()
                .getData().split(":")[1];
    }
}
