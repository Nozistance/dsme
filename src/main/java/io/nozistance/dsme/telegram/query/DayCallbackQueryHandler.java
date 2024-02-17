package io.nozistance.dsme.telegram.query;

import io.nozistance.dsme.service.AnswerTextService;
import io.nozistance.dsme.service.KeyboardService;
import io.nozistance.dsme.service.MenuService;
import io.nozistance.dsme.telegram.UpdateAnswer;
import io.nozistance.dsme.util.DayOfWeek;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class DayCallbackQueryHandler implements CallbackQueryHandler {

    private final MenuService menuService;
    private final KeyboardService keyboards;
    private final AnswerTextService answers;

    @Override
    @SneakyThrows(TelegramApiException.class)
    public void handle(Update update, AbsSender sender) {
        DayOfWeek day = DayOfWeek.values()[Integer.parseInt(args(update))];
        sender.execute(new AnswerCallbackQuery(update.getCallbackQuery().getId()));
        sender.execute(new UpdateAnswer(update, answers.getAnswer("day-menu", day),
                keyboards.singleColumn(menuService.searchByDay(day).stream()
                        .map(i -> Pair.of(i.getName() + " | " + i.getPrice(),
                                "item:" + i.getId()))
                        .toList())));
    }

    @Override
    public String getQuery() {
        return "day";
    }
}
