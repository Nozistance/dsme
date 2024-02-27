package io.nozistance.dsme.aspect;

import io.nozistance.dsme.telegram.UpdateAnswer;
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
public class UpdateHandlerAspect {

    @Around(value = "execution(void io.nozistance.dsme.telegram.UpdateHandler+.handle(..))")
    public Object handle(ProceedingJoinPoint joinPoint) {
        Update update = (Update) joinPoint.getArgs()[0];
        AbsSender sender = (AbsSender) joinPoint.getArgs()[1];
        try {
            return joinPoint.proceed();
        } catch (TelegramApiException ignored) {

        } catch (Throwable e) {
            logError(e);
            answerError(update, sender);
        }
        return null;
    }

    private void answerError(Update update, AbsSender sender) {
        try {
            sender.execute(new UpdateAnswer(update, "SERVER INTERNAL ERROR"));
        } catch (TelegramApiException ignored) {

        }
    }

    private void logError(Throwable e) {
        log.error("Error handling Telegram update: {}", e.getMessage(), e);
    }
}
