package io.nozistance.dsme.telegram.command;

import io.nozistance.dsme.service.AnswerTextService;
import io.nozistance.dsme.service.UserService;
import io.nozistance.dsme.telegram.CommandAnswer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class SubscribeCommandHandler implements CommandHandler {

    private final UserService userService;
    private final AnswerTextService answers;

    @Override
    @SneakyThrows(TelegramApiException.class)
    public void handle(Update update, AbsSender sender) {
        String text = userService.isSubscribed(update)
                ? answers.getAnswer("subscribe-already")
                : answers.getAnswer("subscribe-not-yet");
        userService.subscribe(update);
        sender.execute(new CommandAnswer(update, text));
    }

    @Override
    public String getCommand() {
        return "/subscribe";
    }
}
