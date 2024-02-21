package io.nozistance.dsme.telegram.query;

import io.nozistance.dsme.repository.MenuRepository;
import io.nozistance.dsme.service.AnswerTextService;
import io.nozistance.dsme.service.KeyboardService;
import io.nozistance.dsme.telegram.UpdateAnswer;
import io.nozistance.dsme.util.DayOfWeek;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.springframework.data.util.Pair.of;

@Component
@RequiredArgsConstructor
public class DayCallbackQueryHandler implements CallbackQueryHandler {

    private final MenuRepository menuRepository;
    private final KeyboardService keyboards;
    private final AnswerTextService answers;

    @Override
    @SneakyThrows(TelegramApiException.class)
    public void handle(Update update, AbsSender sender) {
        DayOfWeek day = DayOfWeek.values()[Integer.parseInt(args(update))];
        sender.execute(new AnswerCallbackQuery(update.getCallbackQuery().getId()));
        sender.execute(new UpdateAnswer(update, answers.getAnswer("day-menu", day),
                keyboards.singleColumn(menuRepository.findByDaysOfWeekContains(day).stream()
                        .map(i -> of(i.getName() + " | " + i.getPrice(), "item:" + i.getId()))
                        .toList())));
    }

    @Override
    public String getQuery() {
        return "day";
    }
}
