package io.nozistance.dsme.aspect;

import io.nozistance.dsme.service.AnswerTextService;
import io.nozistance.dsme.telegram.UpdateAnswer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class UpdateHandlerAspect {

    private final AnswerTextService answers;

    @SneakyThrows
    @AfterThrowing(value = "execution(void io.nozistance.dsme.telegram.UpdateHandler+.handle(..))", throwing = "e")
    public void init(JoinPoint joinPoint, Throwable e) {
        Update update = (Update) joinPoint.getArgs()[0];
        AbsSender sender = (AbsSender) joinPoint.getArgs()[1];
        log.error(e.getMessage(), e);
        sender.execute(new UpdateAnswer(update, answers.getAnswer("error")));
    }
}
