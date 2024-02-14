package io.nozistance.dsme.handler.command;

import io.nozistance.dsme.model.User;
import io.nozistance.dsme.service.AnswerTextService;
import io.nozistance.dsme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class StartCommandHandler implements CommandHandler {

    private final UserService userService;
    private final AnswerTextService answers;

    @Override
    public PartialBotApiMethod<?> handle(Update update) {
        User user = userService.getOrCreate(update);
        return SendMessage.builder()
                .chatId(user.getChatId())
                .text(answers.getAnswer("start"))
                .build();
    }

    @Override
    public String getCommand() {
        return "/start";
    }
}
