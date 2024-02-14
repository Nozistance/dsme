package io.nozistance.dsme.handler.command;

import io.nozistance.dsme.handler.UpdateAnswer;
import io.nozistance.dsme.service.AnswerTextService;
import io.nozistance.dsme.service.KeyboardService;
import io.nozistance.dsme.util.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DayCommandHandler implements CommandHandler {

    private final AnswerTextService answers;
    private final KeyboardService keyboards;

    @Override
    public PartialBotApiMethod<?> handle(Update update) {
        return new UpdateAnswer(update, answers.getAnswer("keyboard-day"),
                keyboards.doubleColumn(Arrays.stream(DayOfWeek.values())
                        .map(d -> Pair.of(d.toString(), "day:" + d))
                        .toList())
        );
    }

    @Override
    public String getCommand() {
        return "/day";
    }
}
