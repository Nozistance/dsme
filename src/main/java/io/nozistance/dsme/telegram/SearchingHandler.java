package io.nozistance.dsme.telegram;

import io.nozistance.dsme.repository.MenuRepository;
import io.nozistance.dsme.service.AnswerTextService;
import io.nozistance.dsme.service.KeyboardService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.springframework.data.util.Pair.of;

@Primary
@Component
@RequiredArgsConstructor
public class SearchingHandler implements UpdateHandler {

    private final MenuRepository menuRepository;
    private final AnswerTextService answers;
    private final KeyboardService keyboards;

    @Override
    public boolean supports(Update update) {
        return update.hasMessage()
                && update.getMessage().hasText();
    }

    @Override
    @SneakyThrows(TelegramApiException.class)
    public void handle(Update update, AbsSender sender) {
        String name = update.getMessage().getText();
        var items = menuRepository.findByName(name);
        if (items.isEmpty())
            sender.execute(new UpdateAnswer(update,
                answers.getAnswer("search-nothing", name)));
        else sender.execute(new UpdateAnswer(update,
                answers.getAnswer("search-hints"),
                keyboards.singleColumn(items.stream()
                        .map(i -> of(i.getName(), "item:" + i.getId()))
                        .toList())));
    }
}
