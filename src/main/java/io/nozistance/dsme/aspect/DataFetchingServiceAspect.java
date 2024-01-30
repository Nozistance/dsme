package io.nozistance.dsme.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.UndeclaredThrowableException;

@Slf4j
@Aspect
@Component
public class DataFetchingServiceAspect {

    @AfterThrowing(pointcut = "execution(* io.nozistance.dsme.service.DataFetchingService.getDocument(..))", throwing = "e")
    public void getDocument(JoinPoint joinPoint, UndeclaredThrowableException e) {
        log.error("Error fetching document from URI: {}",
                joinPoint.getArgs()[0], e.getUndeclaredThrowable());
    }
}
