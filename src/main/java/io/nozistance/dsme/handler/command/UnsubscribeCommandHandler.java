package io.nozistance.dsme.handler.command;

import io.nozistance.dsme.handler.UpdateAnswer;
import io.nozistance.dsme.service.AnswerTextService;
import io.nozistance.dsme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class UnsubscribeCommandHandler implements CommandHandler {

    private final UserService userService;
    private final AnswerTextService answers;

    @Override
    public PartialBotApiMethod<?> handle(Update update) {
        String text = userService.isSubscribed(update)
                ? answers.getAnswer("unsubscribe-not-yet")
                : answers.getAnswer("unsubscribe-already");
        userService.unsubscribe(update);
        return new UpdateAnswer(update, text);
    }

    @Override
    public String getCommand() {
        return "/unsubscribe";
    }
}
