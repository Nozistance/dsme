package io.nozistance.dsme.service.handler;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Service
public class CallQueryHandler implements Handler<CallbackQuery> {

    @Override
    public PartialBotApiMethod<?> handle(CallbackQuery callbackQuery) {
        return null;
    }
}
