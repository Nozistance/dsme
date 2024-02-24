package io.nozistance.dsme.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
@Component
public class DataUpdatingServiceAspect {

    @Around("execution(* io.nozistance.dsme.service.DataUpdatingService.update())")
    public Object updateData(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[DSME] Updating...");
        Instant start = Instant.now();
        Object result = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.info("[DSME] Daily menus have been updated in {} ms", timeElapsed);
        log.info("[DSME] Update completed.");
        return result;
    }
}
