package io.nozistance.dsme.service;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;

@FunctionalInterface
public interface Handler<T extends BotApiObject> {

    PartialBotApiMethod<?> handle(T object);
}
