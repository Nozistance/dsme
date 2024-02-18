package io.nozistance.dsme.aspect;

import io.nozistance.dsme.service.AnswerTextService;
import io.nozistance.dsme.telegram.UpdateAnswer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class UpdateHandlerAspect {

    private final AnswerTextService answers;

    @Around("execution(* io.nozistance.dsme.telegram.UpdateHandler+.handle(..))")
    public void init(ProceedingJoinPoint joinPoint) throws TelegramApiException {
        Update update = (Update) joinPoint.getArgs()[0];
        AbsSender sender = (AbsSender) joinPoint.getArgs()[1];
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            sender.execute(new UpdateAnswer(update, answers.getAnswer("error")));
            log.error(e.getMessage(), e);
        }
    }
}
