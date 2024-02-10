package io.nozistance.dsme.handler;

import io.nozistance.dsme.model.Item;
import io.nozistance.dsme.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Primary
@Component
@RequiredArgsConstructor
public class SearchingHandler implements UpdateHandler {

    private final MenuRepository menuRepository;

    @Override
    public boolean supports(Update update) {
        return true;
    }

    @Override
    public PartialBotApiMethod<?> handle(Update update) {
        String name = update.getMessage().getText();
        List<Item> items = menuRepository.findByNameContaining(name);
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(items.toString())
                .build();
    }
}
