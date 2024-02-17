package io.nozistance.dsme.telegram.command;

import io.nozistance.dsme.service.AnswerTextService;
import io.nozistance.dsme.service.KeyboardService;
import io.nozistance.dsme.telegram.UpdateAnswer;
import io.nozistance.dsme.util.DayOfWeek;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DayCommandHandler implements CommandHandler {

    private final AnswerTextService answers;
    private final KeyboardService keyboards;

    @Override
    @SneakyThrows(TelegramApiException.class)
    public void handle(Update update, AbsSender sender) {
        sender.execute(new UpdateAnswer(update,
                answers.getAnswer("keyboard-day"),
                keyboards.doubleColumn(Arrays.stream(DayOfWeek.values())
                        .map(d -> Pair.of(d.toString(), "day:" + d.ordinal()))
                        .toList())
        ));
    }

    @Override
    public String getCommand() {
        return "/day";
    }
}
