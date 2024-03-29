package io.nozistance.dsme.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DocumentFetchingServiceAspect {

    @AfterThrowing(pointcut = "execution(* io.nozistance.dsme.service.impl.DocumentFetchingService.getDocument(..))", throwing = "e")
    public void getDocument(JoinPoint joinPoint, Throwable e) {
        log.error("Error fetching document for: {}",
                joinPoint.getArgs()[0], e);
    }
}
