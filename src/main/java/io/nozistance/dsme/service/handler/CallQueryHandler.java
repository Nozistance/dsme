package io.nozistance.dsme.service.handler;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Service
public class CallQueryHandler implements Handler<CallbackQuery> {

    @Override
    public PartialBotApiMethod<?> handle(CallbackQuery callbackQuery) {
        if (callbackQuery.getData().startsWith("details:")) {
            return details(callbackQuery);
        } else if (callbackQuery.getData().startsWith("day:")) {
            return day(callbackQuery);
        } else return null;
    }

    private PartialBotApiMethod<?> details(CallbackQuery callbackQuery) {
        return null;
    }

    private PartialBotApiMethod<?> day(CallbackQuery callbackQuery) {
        return null;
    }
}
