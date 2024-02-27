package io.nozistance.dsme.telegram.command;

import io.nozistance.dsme.entity.User;
import io.nozistance.dsme.service.AnswerTextService;
import io.nozistance.dsme.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class StartCommandHandler implements CommandHandler {

    private final UserService userService;
    private final AnswerTextService answers;

    @Override
    @SneakyThrows(TelegramApiException.class)
    public void handle(Update update, AbsSender sender) {
        User user = userService.getOrCreate(update);
        sender.execute(SendMessage.builder()
                .chatId(user.getChatId())
                .text(answers.getAnswer("start"))
                .build());
    }

    @Override
    public String getCommand() {
        return "/start";
    }
}
