package io.nozistance.dsme.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.UndeclaredThrowableException;

@Slf4j
@Aspect
public class MenuBotAspect {

    @AfterThrowing(pointcut = "execution(* io.nozistance.springlab.bot.MenuBot.init()", throwing = "e")
    public void init(UndeclaredThrowableException e) {
        log.error(e.getUndeclaredThrowable().getMessage(),
                e.getUndeclaredThrowable());
    }
}
