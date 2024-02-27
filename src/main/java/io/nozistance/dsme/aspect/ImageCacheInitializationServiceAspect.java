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
public class ImageCacheInitializationServiceAspect {

    @Around("execution(* io.nozistance.dsme.service.ImageCacheInitializationService.initialize()))")
    public Object imagePreloadingApplicationRunner(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[DSME] Download image cache...");
        Instant start = Instant.now();
        Object result = joinPoint.proceed();
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toSeconds();
        log.info("[DSME] Image cache have been downloaded in {} s", timeElapsed);
        log.info("[DSME] Downloading completed.");
        return result;
    }
}
