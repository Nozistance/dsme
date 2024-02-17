package io.nozistance.dsme.telegram.query;

import io.nozistance.dsme.telegram.UpdateHandler;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CallbackQueryHandler extends UpdateHandler {

    String getQuery();

    @Override
    default boolean supports(Update update) {
        return update.hasCallbackQuery()
                && update.getCallbackQuery().getData()
                .startsWith(getQuery() + ":");
    }
}
