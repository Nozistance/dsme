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

    @Around("@annotation(org.springframework.transaction.annotation.Transactional) " +
            "&& execution(* io.nozistance.dsme.service.DataUpdatingService.updateData())")
    public void updateData(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();
        joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        log.info("Daily menus have been updated in {} ms", timeElapsed);
    }
}
