package io.nozistance.dsme.telegram;

import io.nozistance.dsme.model.Item;
import io.nozistance.dsme.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Primary
@Component
@RequiredArgsConstructor
public class SearchingHandler implements UpdateHandler {

    private final MenuRepository menuRepository;

    @Override
    public boolean supports(Update update) {
        return update.hasMessage()
                && update.getMessage().hasText();
    }

    @Override
    @SneakyThrows(TelegramApiException.class)
    public void handle(Update update, AbsSender sender) {
        String name = update.getMessage().getText();
        List<Item> items = menuRepository.findByNameContaining(name);
        sender.execute(SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(items.toString())
                .build());
    }
}
