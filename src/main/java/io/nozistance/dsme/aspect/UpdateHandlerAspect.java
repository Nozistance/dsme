package io.nozistance.dsme.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.UndeclaredThrowableException;

@Slf4j
@Aspect
@Component
public class UpdateHandlerAspect {

    @AfterThrowing(pointcut = "execution(* io.nozistance.dsme.telegram.UpdateHandler+.handle(..))", throwing = "e")
    public void init(UndeclaredThrowableException e) {
        log.error(e.getUndeclaredThrowable().getMessage(),
                e.getUndeclaredThrowable());
    }
}
