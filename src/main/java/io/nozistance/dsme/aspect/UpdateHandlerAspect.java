package io.nozistance.dsme.aspect;

import io.nozistance.dsme.telegram.CallbackQueryAnswer;
import io.nozistance.dsme.telegram.CommandAnswer;
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
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
            return null;
        } catch (Throwable e) {
            try {
                if (update.hasCallbackQuery()) {
                    sender.execute(new CallbackQueryAnswer(update, "SERVER INTERNAL ERROR"));
                } else {
                    sender.execute(new CommandAnswer(update, "SERVER INTERNAL ERROR"));
                }
            } catch (TelegramApiException ignored) {}
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
